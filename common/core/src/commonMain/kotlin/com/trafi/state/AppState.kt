package com.trafi.state

import com.trafi.core.ApiResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

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
) : State<AppState, AppAction> {
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

    override fun reduce(event: AppAction): Pair<AppState, Effect<AppAction>> {
        val state = reduceState(event)
        val effect = if (state.fetchNumberFact) {
            flow<AppAction> {
                delay(1000)
                emit(AppAction.NumberFactResponse(ApiResult.Success("Number ${state.count} is great")))
            }.effect("fetch-number-fact", cancelInFlight = true)
        } else {
            Effect.None()
        }
        return state to effect
    }
}
