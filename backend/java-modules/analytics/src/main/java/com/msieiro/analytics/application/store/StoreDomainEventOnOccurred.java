package com.msieiro.analytics.application.store;

import com.msieiro.analytics.domain.AnalyticsDomainEventAggregateId;
import com.msieiro.analytics.domain.AnalyticsDomainEventBody;
import com.msieiro.analytics.domain.AnalyticsDomainEventId;
import com.msieiro.analytics.domain.AnalyticsDomainEventName;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Service
public class StoreDomainEventOnOccurred {

    @Autowired
    private DomainEventStorer storer;

    @RabbitListener(queues = "${rabbitmq.queue.domain_events}")
    public void consumer(DomainEvent event){
        log.info("DomainEvent: {} , reached to EventListener: StoreDomainEventOnOccurred.class", event.eventName());
        AnalyticsDomainEventId id = new AnalyticsDomainEventId(event.getEventId());
        AnalyticsDomainEventAggregateId aggregateId = new AnalyticsDomainEventAggregateId(event.getAggregateId());
        AnalyticsDomainEventName name = new AnalyticsDomainEventName(event.eventName());
        AnalyticsDomainEventBody body = new AnalyticsDomainEventBody(event.toPrimitives());
        storer.store(id, aggregateId, name, body);
    }
}
