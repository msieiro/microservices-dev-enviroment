package com.msieiro.accounts.application.create;

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

    private final AccountRepository accountRepository;
    private final RabbitMQMessagePublisher rabbitMessagePublisher;
    private final RabbitMQEventPublisher rabbitEventPublisher;

    public AccountCreator(final AccountRepository accountRepository, final RabbitMQMessagePublisher rabbitMessagePublisher,
                          final RabbitMQEventPublisher rabbitEventPublisher){
        this.accountRepository = accountRepository;
        this.rabbitMessagePublisher = rabbitMessagePublisher;
        this.rabbitEventPublisher = rabbitEventPublisher;
    }


    public void createAccount(AccountId id, AccountEmail email, AccountPassword password) {
        log.info("AccountCreator is going to create an account with id: {}, email: {}, password: {}", id, email,
            password);
        Account account = Account.create(id, email, password);
        accountRepository.save(account);
        rabbitMessagePublisher.publish(new CreatedAccountNotificationRequest(email.getEmail(), "6698"),
            "internal.exchange", "internal.notifications.routing-key");
        rabbitEventPublisher.publishEvents(account.pullDomainEvents(), "internal.exchange",
            "internal.domain_events.routing-key");
    }
}
