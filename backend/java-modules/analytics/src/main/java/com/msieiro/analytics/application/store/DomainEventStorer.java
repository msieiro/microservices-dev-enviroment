package com.msieiro.analytics.application.store;


import com.msieiro.analytics.domain.*;
import com.msieiro.shared.domain.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public record DomainEventStorer(DomainEventsRepository repository) {

    public void store(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body
    ) {
        AnalyticsDomainEvent domainEvent = new AnalyticsDomainEvent(id, aggregateId, name, body);
        log.info("Created AnalyticsDomainEvent: {}", domainEvent);
        repository.save(domainEvent);
        log.info("Saved AnalyticsDomainEvent: {}", domainEvent);
    }
}
