package com.msieiro.notifications.application;

import com.msieiro.shared.domain.CreatedAccountNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CreatedAccountNotificationService() {
    public void sendNotification(CreatedAccountNotificationRequest notificationRequest) {
        log.info("Trying to send notification to email: {} and authKey: {}", notificationRequest.email(), notificationRequest.authKey());
    }
}
