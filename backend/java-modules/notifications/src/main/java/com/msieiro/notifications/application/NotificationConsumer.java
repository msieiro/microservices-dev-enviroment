package com.msieiro.notifications.application;

import com.msieiro.shared.domain.CreatedAccountNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record NotificationConsumer(CreatedAccountNotificationService createdAccountNotificationService) {

    @RabbitListener(queues = "${rabbitmq.queue.notifications}")
    public void consumer(CreatedAccountNotificationRequest createdAccountNotificationRequest) {
        log.info("Consumed {} from queue", createdAccountNotificationRequest);
        createdAccountNotificationService.sendNotification(createdAccountNotificationRequest);
    }
}
