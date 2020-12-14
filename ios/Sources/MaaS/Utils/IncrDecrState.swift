import ComposableArchitecture
import SwiftUI

struct CounterView: View {
  let store: Store<CounterState, CounterEvent>

  var body: some View {
    WithViewStore(self.store) { viewStore in
      VStack {
        HStack {
            SwiftUI.Button("−") { viewStore.send(.decrement) }
            Text("\(viewStore.count)")
            SwiftUI.Button("+") { viewStore.send(.increment) }
        }
      }
    }
  }
}


#if DEBUG
struct CounterView_Previews: PreviewProvider {

    static func store() -> Store<CounterState, CounterEvent> {
        Store<CounterState, CounterEvent>(
            initialState: CounterState(count: 0),
            reducer: Reducer { state, action, env in
                state = state.reduce(event: action)

                if state.count == 5 {
                    return Effect<CounterEvent, Never>(value: CounterEvent.increment)
                        .cancellable(id: "Count5Incr", cancelInFlight: true)
                        .delay(for: 3, scheduler: env)
                        .eraseToEffect()
                } else {
                    return .none
                }
            },
            environment: DispatchQueue.main.eraseToAnyScheduler()
        )
    }

    static var previews: some View {
        CounterView(store: store())
    }
}
#endif
