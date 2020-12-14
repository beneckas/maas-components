package com.trafi.state

enum class CounterEvent {
    Increment,
    Decrement,
}

data class CounterState(val count: Int) : State<CounterState, CounterEvent> {
    override fun reduce(event: CounterEvent): CounterState = when (event) {
        CounterEvent.Increment -> copy(count = count + 1)
        CounterEvent.Decrement -> copy(count = count - 1)
    }
}
