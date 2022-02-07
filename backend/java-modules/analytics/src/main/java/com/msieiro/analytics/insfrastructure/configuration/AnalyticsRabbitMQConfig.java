package com.msieiro.analytics.insfrastructure.configuration;

import com.msieiro.analytics.application.store.DomainEventConsumer;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AnalyticsRabbitMQConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.domain_events}")
    private String domainEventsQueue;

    @Value("${rabbitmq.routing-keys.internal-domain_events}")
    private String internalDomainEventsRoutingKey;

    @Autowired
    private DomainEventConsumer domainEventConsumer;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue domainEventsQueue() {
        return new Queue(this.domainEventsQueue);
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder.bind(domainEventsQueue()).to(internalTopicExchange())
            .with(this.internalDomainEventsRoutingKey);
    }
}
