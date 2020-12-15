import SwiftUI
import Combine
import ComposableArchitecture

struct AppView: View {
  let store: Store<AppState, AppAction>

  var body: some View {
    WithViewStore(self.store) { viewStore in
        VStack {
            HStack {
                SwiftUI.Button("−") { viewStore.send(.DecrementButtonTapped()) }
                Text("\(viewStore.count)")
                SwiftUI.Button("+") { viewStore.send(.IncrementButtonTapped()) }
            }

            SwiftUI.Button("Number fact") { viewStore.send(.NumberFactButtonTapped()) }
        }
        .alert(
            item: viewStore.binding(
                get: { $0.numberFactAlert.map(FactAlert.init(title:)) },
                send: .FactAlertDismissed()
            ),
            content: { Alert(title: Text($0.title)) }
        )
    }
  }
}

struct FactAlert: Identifiable {
  var title: String
  var id: String { self.title }
}


#if DEBUG
struct AppView_Previews: PreviewProvider {

    static func store() -> Store<AppState, AppAction> {
        Store<AppState, AppAction>(
            initialState: AppState(
                count: 0,
                numberFactAlert: nil,
                fetchNumberFact: false),
            reducer: Reducer(AppState.reduce),
            environment: ()
        )
    }

    static var previews: some View {
        Group {
            AppView(store: store())
        }
    }
}
#endif

