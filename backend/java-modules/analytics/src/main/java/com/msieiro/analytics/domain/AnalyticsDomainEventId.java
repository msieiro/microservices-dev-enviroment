package com.msieiro.analytics.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@EqualsAndHashCode
public final class AnalyticsDomainEventId implements Serializable {

    protected String eventId;

    public AnalyticsDomainEventId(String eventId) {
        ensureValidUuid(eventId);
        this.eventId = eventId;
    }

    protected AnalyticsDomainEventId() {
        this.eventId = null;
    }

    private void ensureValidUuid(String eventId) throws IllegalArgumentException {
        UUID.fromString(eventId);
    }
}

