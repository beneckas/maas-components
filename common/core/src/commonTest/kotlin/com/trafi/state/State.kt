package com.trafi.state

import com.trafi.test.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.test.Test

enum class Event {
    Increment,
    Decrement,
}

data class CounterState(val count: Int) : State<CounterState, Event> {
    override fun reduce(event: Event): CounterState = when (event) {
        Event.Increment -> copy(count = count + 1)
        Event.Decrement -> copy(count = count - 1)
    }
}

class StateTests {
    @Test
    fun stateTest() {
        val machine = StateMachine(CounterState(0))
        println(machine.stateFlow.value)

        runBlocking {
            machine.stateFlow.collect {
                println("Count ${it.count}")
                delay(1000)
                machine.transition(Event.Increment)
                machine.transition(Event.Increment)
            }
        }
    }
}
