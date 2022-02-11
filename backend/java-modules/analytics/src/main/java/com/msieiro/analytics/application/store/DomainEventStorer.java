package com.msieiro.analytics.application.store;

import com.msieiro.analytics.domain.*;
import com.msieiro.shared.domain.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class DomainEventStorer {

    private final DomainEventsRepository repository;

    public DomainEventStorer(final DomainEventsRepository repository){
        this.repository = repository;
    }

    public void store(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body) {
        AnalyticsDomainEvent domainEvent = new AnalyticsDomainEvent(id, aggregateId, name, body);
        log.info("Created AnalyticsDomainEvent: {}", domainEvent);
        AnalyticsDomainEvent savedDomainEvent = repository.save(domainEvent);
        log.info("Saved AnalyticsDomainEvent: {}", savedDomainEvent);
    }
}
