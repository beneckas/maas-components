import ComposableArchitecture
import Combine

public extension Reducer where State: AnyObject, Action: AnyObject, Environment == Void {
    init(_ reducer: @escaping (State) -> (Action) -> KotlinPair<State, MaasCore.Effect<Action>>) {
        self.init { state, action, env in
            let pair = reducer(state)(action)
            state = pair.first ?? state
            return .from(pair.second)
        }
    }
}

private extension ComposableArchitecture.Effect where Output: AnyObject, Failure == Never {

    static func from(_ effect: MaasCore.Effect<Output>?) -> Self {
        switch effect {
        case let flow as EffectFlow<Output>:
            return .from(flow.flow)
        case let cancel as EffectCancel<Output>:
            return .cancel(id: cancel.id)
        case let effects as Effects<Output>:
            return .merge(effects.effects.map { .from($0) })
        default:
            return .none
        }
    }

    static func from(_ flow: CFlow<Output>) -> Self {
        let effect = Self(Publishers.KotlinFlowPublisher(flow))
        if let id = flow.cancellationId {
            return effect.cancellable(id: id, cancelInFlight: flow.cancelInFlight)
        } else {
            return effect
        }
    }
}


// Source: https://medium.com/flawless-app-stories/swift-combine-custom-publisher-6d1cc3dc248f
private extension Publishers {

    class KotlinFlowSubscription<T: AnyObject, S: Subscriber>: Subscription where S.Input == T, S.Failure == Never {

        var subscriber: S?
        var closable: Ktor_ioCloseable?

        init(_ flow: CFlow<T>, _ subscriber: S) {
            self.subscriber = subscriber
            self.closable = flow.watch {
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

        private let flow: CFlow<T>
        init(_ flow: CFlow<T>) {
            self.flow = flow
        }

        func receive<S>(subscriber: S) where S: Subscriber, S.Failure == Never, S.Input == T {
            subscriber.receive(subscription: KotlinFlowSubscription(flow, subscriber))
        }
    }
}

