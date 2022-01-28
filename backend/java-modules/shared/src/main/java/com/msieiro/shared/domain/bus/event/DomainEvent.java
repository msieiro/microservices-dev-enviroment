package com.msieiro.shared.domain.bus.event;

import com.msieiro.shared.domain.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class DomainEvent {
    private String aggregateId;
    private String eventId;
    private String occurredOn;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Utils.dateToString(LocalDateTime.now());
    }

    public abstract String eventName();

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn);
}
