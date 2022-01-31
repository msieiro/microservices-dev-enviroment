package com.msieiro.shared.domain.bus.event;

import java.util.List;

public interface EventBus {
    void publishEvents(final List<DomainEvent> domainEvents, final String exchange, final String routingKey);
}
