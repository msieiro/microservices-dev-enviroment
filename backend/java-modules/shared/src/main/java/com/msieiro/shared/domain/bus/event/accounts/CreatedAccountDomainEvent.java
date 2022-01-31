package com.msieiro.shared.domain.bus.event.accounts;

import com.msieiro.shared.domain.bus.event.DomainEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;

@Data
@EqualsAndHashCode
public class CreatedAccountDomainEvent extends DomainEvent {

    private String firstName;
    private String lastName;
    private String email;

    public CreatedAccountDomainEvent() {
        super(null);
        this.firstName = null;
        this.lastName = null;
        this.email = null;
    }

    public CreatedAccountDomainEvent(String aggregateId, String firstName, String lastName, String email) {
        super(aggregateId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CreatedAccountDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String name,
        String lastName,
        String email
    ) {
        super(aggregateId, eventId, occurredOn);
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String eventName() {
        return "account.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("firstName", firstName);
            put("lastName", lastName);
            put("email", email);
        }};
    }

    @Override
    public CreatedAccountDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new CreatedAccountDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("firstName"),
            (String) body.get("lastName"),
            (String) body.get("email")
        );
    }

}
