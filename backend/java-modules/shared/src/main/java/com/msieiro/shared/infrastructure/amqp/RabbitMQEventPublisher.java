package com.msieiro.shared.infrastructure.amqp;

import com.msieiro.shared.domain.bus.event.DomainEvent;
import com.msieiro.shared.domain.bus.event.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitMQEventPublisher implements EventBus {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void publishEvents(final List<DomainEvent> domainEvents, String exchange, String routingKey) {
        log.info("Publishing a total of {} events", domainEvents.size());
        domainEvents.forEach(domainEvent -> {
            log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, domainEvent.toString());
            amqpTemplate.convertAndSend(exchange, routingKey, domainEvent);
            log.info("Published! to {} using routingKey {}. Payload: {}", exchange, routingKey, domainEvent.toString());
        });
    }
}
