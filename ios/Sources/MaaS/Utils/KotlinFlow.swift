import ComposableArchitecture
import Combine

// Source: https://medium.com/flawless-app-stories/swift-combine-custom-publisher-6d1cc3dc248f
extension Publishers {

    private class KotlinFlowSubscription<T: AnyObject, S: Subscriber>: Subscription where S.Input == T, S.Failure == Never {

        private var subscriber: S?
        private var closable: Ktor_ioCloseable?

        init(_ flow: Kotlinx_coroutines_coreFlow, _ subscriber: S) {
            self.subscriber = subscriber
            self.closable = CFlow<T>(origin: flow).watch {
                guard let e = $0 else { return }
                _ = subscriber.receive(e)
            }
        }

        func request(_ demand: Subscribers.Demand) {
            // Optionally adjust the demand
            // fatalError("DEMANT: \(demand)")
        }

        func cancel() {
            closable?.close()
            closable = nil
            subscriber = nil
        }

    }

    class KotlinFlowPublisher<T: AnyObject>: Publisher {

        typealias Output = T
        typealias Failure = Never

        private let flow: Kotlinx_coroutines_coreFlow
        init(_ flow: Kotlinx_coroutines_coreFlow) {
            self.flow = flow
        }

        func receive<S>(subscriber: S) where S: Subscriber, S.Failure == Never, S.Input == T {
            subscriber.receive(subscription: KotlinFlowSubscription(flow, subscriber))
        }
    }
}

extension Effect where Output: AnyObject, Failure == Never {

    init(_ flow: Kotlinx_coroutines_coreFlow?) {
        if let flow = flow {
            self.init(Publishers.KotlinFlowPublisher(flow))
        } else {
            self.init(Empty(completeImmediately: true))
        }
    }
}

extension Reducer where State: AnyObject, Action: AnyObject, Environment == Void {
    init(_ reducer: @escaping (State) -> (Action) -> KotlinPair<State, Kotlinx_coroutines_coreFlow>) {
        self.init { state, action, env in
            let pair = reducer(state)(action)
            state = pair.first ?? state
            return Effect(pair.second)
        }
    }
}
