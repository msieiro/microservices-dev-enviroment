package com.msieiro.analytics.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@EqualsAndHashCode
public final class AnalyticsDomainEventAggregateId implements Serializable {

    protected String aggregateId;

    public AnalyticsDomainEventAggregateId(String aggregateId) {
        ensureValidUuid(aggregateId);
        this.aggregateId = aggregateId;
    }

    protected AnalyticsDomainEventAggregateId() {
        this.aggregateId = null;
    }

    private void ensureValidUuid(String aggregateId) throws IllegalArgumentException {
        UUID.fromString(aggregateId);
    }
}
