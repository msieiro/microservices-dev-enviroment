package com.msieiro.analytics.domain;

import com.msieiro.shared.domain.Identifier;

import javax.persistence.Embeddable;

@Embeddable
public final class AnalyticsDomainEventId extends Identifier {
    public AnalyticsDomainEventId() {
        super(null);
    }

    public AnalyticsDomainEventId(String value) {
        super(value);
    }
}
