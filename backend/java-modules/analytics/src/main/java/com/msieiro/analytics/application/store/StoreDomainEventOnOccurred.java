package com.msieiro.analytics.application.store;

import com.msieiro.analytics.domain.AnalyticsDomainEventAggregateId;
import com.msieiro.analytics.domain.AnalyticsDomainEventBody;
import com.msieiro.analytics.domain.AnalyticsDomainEventId;
import com.msieiro.analytics.domain.AnalyticsDomainEventName;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.event.DomainEvent;
import com.msieiro.shared.domain.bus.event.DomainEventJsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Service
public class StoreDomainEventOnOccurred {

    @Autowired
    private DomainEventStorer storer;

    @Autowired
    private DomainEventJsonDeserializer deserializer;

    @RabbitListener(queues = "${rabbitmq.queue.domain_events}", autoStartup = "true")
    public void consumer(Message message)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        log.info("New message reached the domain events handler : {}", message.toString());
        String serializedMessage = new String(message.getBody());
        DomainEvent domainEvent = deserializer.deserialize(serializedMessage);
        log.info("DomainEvent: {} , reached to EventListener: StoreDomainEventOnOccurred.class ---> {}",
            domainEvent.eventName(), domainEvent);
        AnalyticsDomainEventId id = new AnalyticsDomainEventId(domainEvent.getEventId());
        AnalyticsDomainEventAggregateId aggregateId = new AnalyticsDomainEventAggregateId(domainEvent.getAggregateId());
        AnalyticsDomainEventName name = new AnalyticsDomainEventName(domainEvent.eventName());
        AnalyticsDomainEventBody body = new AnalyticsDomainEventBody(domainEvent.toPrimitives());
        storer.store(id, aggregateId, name, body);
    }
}
