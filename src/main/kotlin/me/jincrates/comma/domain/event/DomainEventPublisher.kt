package me.jincrates.comma.domain.event

interface DomainEventPublisher<T : DomainEvent<T>> {
    fun publish(domainEvent: T)
}
