package com.msieiro.shared.domain.bus.event.accounts;

import com.msieiro.shared.domain.bus.event.DomainEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

@Data
@EqualsAndHashCode
@ToString
public final class AccountCreatedDomainEvent extends DomainEvent {

    private String email;
    private String password;

    public AccountCreatedDomainEvent() {
        super(null);
        this.email = null;
        this.password = null;
    }

    public AccountCreatedDomainEvent(String aggregateId, String email, String password) {
        super(aggregateId);
        this.email = email;
        this.password = password;
    }

    public AccountCreatedDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String email,
        String password) {
        super(aggregateId, eventId, occurredOn);
        this.email = email;
        this.password = password;
    }

    @Override
    public String eventName() {
        return "account.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {
            {
                put("email", email);
                put("password", password);
            }
        };
    }

    @Override
    public AccountCreatedDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn) {
        return new AccountCreatedDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("email"),
            (String) body.get("password"));
    }

}
