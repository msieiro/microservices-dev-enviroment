package com.msieiro.analytics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public final class AnalyticsDomainEvent {

    @EmbeddedId
    private AnalyticsDomainEventId id;

    private AnalyticsDomainEventAggregateId aggregateId;

    private AnalyticsDomainEventName name;

    private AnalyticsDomainEventBody body;

}
