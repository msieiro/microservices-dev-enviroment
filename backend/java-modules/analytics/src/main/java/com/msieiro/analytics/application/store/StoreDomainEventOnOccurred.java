package com.msieiro.analytics.application.store;

import com.msieiro.analytics.domain.AnalyticsDomainEventAggregateId;
import com.msieiro.analytics.domain.AnalyticsDomainEventBody;
import com.msieiro.analytics.domain.AnalyticsDomainEventId;
import com.msieiro.analytics.domain.AnalyticsDomainEventName;
import com.msieiro.shared.domain.bus.event.DomainEvent;
import com.msieiro.shared.domain.bus.event.DomainEventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
@DomainEventSubscriber(getValue = {DomainEvent.class})
public record StoreDomainEventOnOccurred(DomainEventStorer storer) {

    @EventListener
    public void on(DomainEvent event) {
        log.info("DomainEvent: {} , reached to EventListener: StoreDomainEventOnOccurred", event.toString());
        AnalyticsDomainEventId id = new AnalyticsDomainEventId(event.getEventId());
        AnalyticsDomainEventAggregateId aggregateId = new AnalyticsDomainEventAggregateId(event.getAggregateId());
        AnalyticsDomainEventName name = new AnalyticsDomainEventName(event.eventName());
        AnalyticsDomainEventBody body = new AnalyticsDomainEventBody(event.toPrimitives());
        storer.store(id, aggregateId, name, body);
    }
}
