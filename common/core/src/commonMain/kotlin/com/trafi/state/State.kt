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
    fun reduce(event: E): Pair<T, Effect<E>>
}

class StateMachine<T : State<T, E>, E>(initial: T) {
    private val _stateFlow = MutableStateFlow(initial)
    val stateFlow: StateFlow<T> = _stateFlow

    fun transition(event: E) {
        _stateFlow.value = _stateFlow.value.reduce(event).first
    }
}

sealed class Effect<T> {
    class Flow<T>(val flow: CFlow<T>) : Effect<T>()
    class Cancel<T>(val id: String) : Effect<T>()
    class None<T>: Effect<T>()
}

class Effects<T>(val effects: List<Effect<T>>) : Effect<T>()


fun <T> Flow<T>.effect(cancellationId: String, cancelInFlight: Boolean = false): Effect<T> = Effect.Flow(CFlow(this, cancellationId, cancelInFlight))
fun <T> Flow<T>.effect(): Effect<T> = Effect.Flow(CFlow(this))

class CFlow<T>(private val origin: Flow<T>, val cancellationId: String? = null, val cancelInFlight: Boolean = true) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
