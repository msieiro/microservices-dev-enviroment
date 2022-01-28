package com.msieiro.analytics.domain;

public interface DomainEventsRepository {
    void save(AnalyticsDomainEvent event);
}
