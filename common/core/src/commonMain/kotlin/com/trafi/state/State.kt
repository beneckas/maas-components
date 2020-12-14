package com.trafi.state

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

fun <T> Flow<T>.wrap(): CFlow<T> = CFlow(this)

class CFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Default + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
