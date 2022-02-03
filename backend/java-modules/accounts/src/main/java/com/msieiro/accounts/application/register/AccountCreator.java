package com.msieiro.accounts.application.register;

import com.msieiro.accounts.domain.*;
import com.msieiro.clients.notifications.CreatedAccountNotificationRequest;
import com.msieiro.shared.infrastructure.amqp.RabbitMQEventPublisher;
import com.msieiro.shared.infrastructure.amqp.RabbitMQMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountCreator {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RabbitMQMessagePublisher rabbitMessagePublisher;
    @Autowired
    private RabbitMQEventPublisher rabbitEventPublisher;

    public void createAccount(AccountId id, AccountEmail email, AccountPassword password) {
        log.info("AccountCreator is going to create an account with id: {}, email: {}, password: {}", id, email,
            password);
        Account account = Account.create(id, email, password);
        accountRepository.save(account);
        rabbitMessagePublisher.publish(new CreatedAccountNotificationRequest(email.getEmail(), email.getEmail()),
            "internal.exchange", "internal.notifications.routing-key");
        rabbitEventPublisher.publishEvents(account.pullDomainEvents(), "internal.exchange",
            "internal.domain_events.routing-key");
    }
}
