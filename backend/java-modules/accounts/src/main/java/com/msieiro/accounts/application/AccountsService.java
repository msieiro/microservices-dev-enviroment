package com.msieiro.accounts.application;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.infrastructure.persistence.AccountRepository;
import com.msieiro.clients.notifications.CreatedAccountNotificationRequest;
import com.msieiro.shared.application.RabbitMQMessageProducer;
import org.springframework.stereotype.Service;

@Service
public record AccountsService(AccountRepository accountRepository, RabbitMQMessageProducer rabbitMQMessageProducer) {
    public void registerAccount(AccountRegistrationRequest request) {
        // todo: Check if email exists
        Account account = Account.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        accountRepository.save(account);
        rabbitMQMessageProducer.publish(new CreatedAccountNotificationRequest(request.email(), request.email()), "internal.exchange", "internal.notifications.routing-key");
    }
}
