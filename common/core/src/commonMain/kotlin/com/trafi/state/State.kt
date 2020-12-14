package com.trafi.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface State<out T : State<T, E>, E> {
    fun reduce(event: E): T
    fun reduceFlow(event: E): Pair<T, Flow<E>>
}

class StateMachine<T : State<T, E>, E>(initial: T) {
    private val _stateFlow = MutableStateFlow(initial)
    val stateFlow: StateFlow<T> = _stateFlow

    fun transition(event: E) {
        _stateFlow.value = _stateFlow.value.reduce(event)
    }
}
