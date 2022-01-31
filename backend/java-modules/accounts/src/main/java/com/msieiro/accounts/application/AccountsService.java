package com.msieiro.accounts.application;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.infrastructure.persistence.AccountRepository;
import com.msieiro.clients.notifications.CreatedAccountNotificationRequest;
import com.msieiro.shared.infrastructure.amqp.RabbitMQEventPublisher;
import com.msieiro.shared.infrastructure.amqp.RabbitMQMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AccountsService(AccountRepository accountRepository, RabbitMQMessagePublisher rabbitMessagePublisher,
                              RabbitMQEventPublisher rabbitEventPublisher) {
    public void registerAccount(AccountRegistrationRequest request) {
        log.info("AccountsService is going to register an account: {}", request.toString());
        Account account = Account.create(request.id(), request.firstName(), request.lastName(), request.email());
        accountRepository.save(account);
        rabbitMessagePublisher.publish(new CreatedAccountNotificationRequest(request.email(), request.email()), "internal.exchange", "internal.notifications.routing-key");
        rabbitEventPublisher.publishEvents(account.pullDomainEvents(), "internal.exchange","internal.domain_events.routing-key");
    }
}
