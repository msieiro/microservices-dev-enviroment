package com.msieiro.shared.infrastructure.amqp;

import com.msieiro.shared.domain.bus.event.DomainEvent;
import com.msieiro.shared.domain.bus.event.DomainEventJsonSerializer;
import com.msieiro.shared.domain.bus.event.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitMQEventPublisher implements EventBus {

    private final String events_exchange = "internal.exchange";
    private final String events_key = "internal.domain_events.routing-key";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQEventPublisher(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvents(List<DomainEvent> domainEvents, String exchange, String routingKey) throws AmqpException {
        log.info("Publishing a total of {} events", domainEvents.size());
        domainEvents.forEach(domainEvent -> {
            log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, domainEvent.toString());
            this.publish(domainEvent, exchange, routingKey);
            // rabbitTemplate.convertAndSend(exchange, routingKey, domainEvent);
            log.info("Published! to {} using routingKey {}. Payload: {}", exchange, routingKey, domainEvent);
        });
    }

    @Override
    public void publish(final List<DomainEvent> domainEvents) {
        domainEvents.forEach(domainEvent -> {
            log.info("Publishing to {} using routingKey {}. Payload: {}", events_exchange, events_key, domainEvent.toString());
            String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

            Message message = new Message(
                serializedDomainEvent.getBytes(),
                MessagePropertiesBuilder.newInstance()
                    .setContentEncoding("utf-8")
                    .setContentType("application/json")
                    .build());

            rabbitTemplate.send(events_exchange, events_key, message);
            // rabbitTemplate.convertAndSend(exchange, routingKey, domainEvent);
            log.info("Published! to {} using routingKey {}. Payload: {}", events_exchange, events_key, domainEvent);
        });
    }

    public void publish(DomainEvent domainEvent, String exchangeName, String routingKey) throws AmqpException {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(
            serializedDomainEvent.getBytes(),
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .build());

        rabbitTemplate.send(exchangeName, routingKey, message);
    }
}
