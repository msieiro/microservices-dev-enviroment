package com.msieiro.shared.infrastructure;

import com.msieiro.shared.domain.UuidGenerator;
import com.msieiro.shared.domain.bus.command.Command;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.event.DomainEvent;
import com.msieiro.shared.domain.bus.event.EventBus;
import com.msieiro.shared.domain.bus.query.Query;
import com.msieiro.shared.domain.bus.query.QueryBus;
import com.msieiro.shared.domain.bus.query.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public abstract class UnitTestCase {

    protected EventBus eventBus;
    protected QueryBus queryBus;
    protected CommandBus commandBus;
    protected UuidGenerator uuidGenerator;
    protected PasswordEncoder passwordEncoder;
    protected RabbitTemplate rabbitTemplate;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
        queryBus = mock(QueryBus.class);
        commandBus = mock(CommandBus.class);
        uuidGenerator = mock(UuidGenerator.class);
        passwordEncoder = new BCryptPasswordEncoder();
        rabbitTemplate = mock(RabbitTemplate.class);
    }

    public void shouldHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }

    public void shouldHaveDispatched(List<Command> commands) {
        verify(commandBus, atLeastOnce()).dispatches(commands);
    }

    public void shouldHavePublished(Command command) {
        shouldHaveDispatched(Collections.singletonList(command));
    }

    public void shouldGenerateUuid(String uuid) {
        when(uuidGenerator.generate()).thenReturn(uuid);
    }

    public void shouldGenerateUuids(String uuid, String... others) {
        when(uuidGenerator.generate()).thenReturn(uuid, others);
    }

    public void shouldAsk(Query query, Response response) {
        when(queryBus.ask(query)).thenReturn(response);
    }
}
