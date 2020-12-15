package com.trafi.state

import com.trafi.core.ApiResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
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
    override fun reduce(event: AppAction): AppState = when (event) {
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

    override fun reduceFlow(event: AppAction): Pair<AppState, Effect<AppAction>> {
        val newState = reduce(event)
        val effect = if (newState.fetchNumberFact) {
            flow<AppAction> {
                delay(1000)
                emit(AppAction.NumberFactResponse(ApiResult.Success("Number ${newState.count} is great")))
            }.effect("fetch-number ${newState.count}", cancelInFlight = true)
        } else {
            Effect.None()
        }
        return reduce(event) to effect
    }
}
