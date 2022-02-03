package com.msieiro.analytics.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
public final class AnalyticsDomainEventName implements Serializable {
    private String eventName;

    public AnalyticsDomainEventName() {
        this.eventName = null;
    }

    public AnalyticsDomainEventName(String eventName) {
        this.eventName = eventName;
    }
}
