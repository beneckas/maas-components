package com.trafi.state

import com.trafi.core.ApiResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class AppEnvironment {
    var fetchNumberFact: (count: Int) -> Effect<AppAction> = { count ->
        flow<AppAction> {
            delay(1000)
            AppAction.NumberFactResponse(ApiResult.Success("Number $count is great"))
        }.effect("fetch-number-fact", cancelInFlight = true)
    }
}

sealed class AppAction {
    object FactAlertDismissed : AppAction()
    object DecrementButtonTapped : AppAction()
    object IncrementButtonTapped : AppAction()
    object NumberFactButtonTapped : AppAction()
    class NumberFactResponse(val result: ApiResult<String>) : AppAction()
}

data class AppState(
    val count: Int,
    val numberFactAlert: String?,
    val fetchNumberFact: Boolean = false
) : Reducer<AppState, AppAction, AppEnvironment> {
    private fun reduceState(event: AppAction): AppState = when (event) {
        AppAction.FactAlertDismissed -> copy(numberFactAlert = null)
        AppAction.DecrementButtonTapped -> copy(count = count - 1)
        AppAction.IncrementButtonTapped -> copy(count = count + 1)
        AppAction.NumberFactButtonTapped -> copy(fetchNumberFact = true)
        is AppAction.NumberFactResponse -> copy(
            numberFactAlert = (event.result as? ApiResult.Success)?.value
                ?: "Could not load a number fact",
            fetchNumberFact = false
        )
    }

    override fun AppState.reduce(
        action: AppAction,
        environment: AppEnvironment,
    ): Pair<AppState, Effect<AppAction>> {
        val state = reduceState(action)
        val effect = if (state.fetchNumberFact) {
            environment.fetchNumberFact(state.count)
        } else {
            Effect.None()
        }
        return state to effect
    }

}
