package com.msieiro.accounts.application.create;

import com.msieiro.accounts.AccountsModuleUnitTestCase;
import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountCreatedDomainEventMother;
import com.msieiro.accounts.domain.AccountMother;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.event.accounts.AccountCreatedDomainEvent;
import com.msieiro.shared.infrastructure.amqp.RabbitMQEventPublisher;
import com.msieiro.shared.infrastructure.amqp.RabbitMQMessagePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public final class CreateAccountCommandHandlerShould extends AccountsModuleUnitTestCase {

    private CreateAccountCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        handler = new CreateAccountCommandHandler(
            new AccountCreator(repository,
            new RabbitMQMessagePublisher(rabbitTemplate),
            new RabbitMQEventPublisher(rabbitTemplate)));
    }

    @Test
    void create_a_valid_account() {
        CreateAccountCommand command = CreateAccountCommandMother.random();
        Account account      = AccountMother.fromRequest(command);
        handler.handle(command);
        shouldHaveSaved(account);
    }
}
