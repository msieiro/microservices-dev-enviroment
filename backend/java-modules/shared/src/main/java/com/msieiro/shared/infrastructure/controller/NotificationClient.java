package com.msieiro.shared.infrastructure.controller;

import com.msieiro.shared.domain.CreatedAccountNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "notifications",
    url = "${clients.notifications.url}"
)
public interface NotificationClient {

    @PostMapping("api/v1/notifications")
    ResponseEntity<?> sendNotificationToCreatedAccount(@RequestBody CreatedAccountNotificationRequest notificationRequest);
}
