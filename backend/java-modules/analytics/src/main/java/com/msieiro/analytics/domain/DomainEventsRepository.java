package com.msieiro.analytics.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventsRepository
    extends JpaRepository<AnalyticsDomainEvent, AnalyticsDomainEventId> {
}
