package com.trafi.state

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

enum class CounterEvent {
    Increment,
    Decrement,
}

data class CounterState(val count: Int) : State<CounterState, CounterEvent> {
    override fun reduce(event: CounterEvent): CounterState = when (event) {
        CounterEvent.Increment -> copy(count = count + 1)
        CounterEvent.Decrement -> copy(count = count - 1)
    }

    override fun reduceFlow(event: CounterEvent): Pair<CounterState, Effect<CounterEvent>> {
        return reduce(event) to flow {
            delay(500)
            emit(CounterEvent.Increment)
        }.effect()
    }
}
