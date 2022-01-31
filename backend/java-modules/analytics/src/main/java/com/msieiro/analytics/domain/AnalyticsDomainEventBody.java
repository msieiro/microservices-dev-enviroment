package com.msieiro.analytics.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
public final class AnalyticsDomainEventBody{
    private HashMap<String, Serializable> eventBody;
    public AnalyticsDomainEventBody(HashMap<String, Serializable> eventBody) {
        this.eventBody = eventBody;
    }
    public AnalyticsDomainEventBody() {
        this.eventBody = null;
    }
}
