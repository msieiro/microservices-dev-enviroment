package com.msieiro.notifications.application;

import com.msieiro.shared.domain.CreatedAccountNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreatedAccountNotificationConsumer {

    @Autowired
    private CreatedAccountNotificationSender createdAccountNotificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notifications}")
    public void consumer(CreatedAccountNotificationRequest createdAccountNotificationRequest) {
        log.info("Consumed {} from queue", createdAccountNotificationRequest);
        createdAccountNotificationService.sendNotification(createdAccountNotificationRequest);
    }
}
