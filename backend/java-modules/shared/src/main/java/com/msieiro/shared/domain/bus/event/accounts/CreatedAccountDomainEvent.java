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
public final class CreatedAccountDomainEvent extends DomainEvent {

    private String email;
    private String password;

    public CreatedAccountDomainEvent() {
        super(null);
        this.email = null;
        this.password = null;
    }

    public CreatedAccountDomainEvent(String aggregateId, String email, String password) {
        super(aggregateId);
        this.email = email;
        this.password = password;
    }

    public CreatedAccountDomainEvent(
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
    public CreatedAccountDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn) {
        return new CreatedAccountDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("email"),
            (String) body.get("password"));
    }

}
