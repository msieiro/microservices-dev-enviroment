package com.msieiro.analytics.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomainEventsConsumerRepository
    extends JpaRepository<AnalyticsDomainEvent, AnalyticsDomainEventId> {
    @Query(value = "SELECT * FROM analytics_domain_event ORDER BY created_date ASC LIMIT :chunk", nativeQuery = true)
    List<AnalyticsDomainEvent> findAllOrderByCreatedDate(@Param("chunk") Integer chunk);
}
