package com.msieiro.analytics.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//SELECT * FROM domain_events ORDER BY occurred_on ASC LIMIT :chunk
public interface DomainEventsRepository
    extends JpaRepository<AnalyticsDomainEvent, AnalyticsDomainEventId> {
}
