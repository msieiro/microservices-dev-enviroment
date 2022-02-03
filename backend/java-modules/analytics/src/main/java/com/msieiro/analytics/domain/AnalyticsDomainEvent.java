package com.msieiro.analytics.domain;

import com.msieiro.shared.domain.AuditedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public final class AnalyticsDomainEvent extends AuditedEntity implements Serializable {

    @EmbeddedId
    private AnalyticsDomainEventId id;
    @Embedded
    private AnalyticsDomainEventAggregateId aggregateId;
    @Embedded
    private AnalyticsDomainEventName name;
    @Embedded
    private AnalyticsDomainEventBody body;

    public AnalyticsDomainEvent(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.name = name;
        this.body = body;
    }
}
