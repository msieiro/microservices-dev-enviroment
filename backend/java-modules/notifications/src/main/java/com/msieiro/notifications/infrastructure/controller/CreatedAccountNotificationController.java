/**
 * package com.msieiro.notifications.infrastructure.controller;
 * <p>
 * import com.msieiro.notifications.application.CreatedAccountNotificationService;
 * import com.msieiro.shared.domain.CreatedAccountNotificationRequest;
 * import lombok.extern.slf4j.Slf4j;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 *
 * @Slf4j
 * @RestController
 * @RequestMapping("api/v1/notifications") public record CreatedAccountNotificationController(
 * CreatedAccountNotificationService createdAccountNotificationService) {
 * @PostMapping public ResponseEntity<?> sendNotificationToCreatedAccount(@RequestBody CreatedAccountNotificationRequest notificationRequest) {
 * log.info("Received notification request of: {} {}", notificationRequest.email(), notificationRequest.authKey());
 * createdAccountNotificationService.sendNotification(notificationRequest);
 * return new ResponseEntity<>(HttpStatus.ACCEPTED);
 * }
 * }
 */
