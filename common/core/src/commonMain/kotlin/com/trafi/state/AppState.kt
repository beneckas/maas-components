package com.trafi.state

import com.trafi.core.ApiResult

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
}
