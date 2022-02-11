package com.msieiro.analytics.application.store;

import com.msieiro.analytics.domain.AnalyticsDomainEvent;
import com.msieiro.analytics.domain.DomainEventsConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class DomainEventConsumer {

    private boolean shouldStop = false;
    private final Integer CHUNKS = 5;

    @Autowired
    private DomainEventsConsumerRepository domainEventsConsumerRepository;

    protected DomainEventConsumer(DomainEventsConsumerRepository domainEventsConsumerRepository) {
        this.domainEventsConsumerRepository = domainEventsConsumerRepository;
        //this.consume();
    }

    @Transactional(rollbackFor = Exception.class)
    public void consume() {
        log.warn("The DomainEventConsumer started to consume");
        while (!shouldStop) {
            List<AnalyticsDomainEvent> events = domainEventsConsumerRepository.findAllOrderByCreatedDate(CHUNKS);
            log.warn("The AnalyticsDomainEvent list have a size of : {}", events.size());
            /*try {
                for (Object[] event : events) {
                    executeSubscribers(
                        (String) event[0],
                        (String) event[1],
                        (String) event[2],
                        (String) event[3],
                        (Timestamp) event[4]
                    );
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }

            sessionFactory.getCurrentSession().clear();*/
            shouldStop = true;
        }
    }
}
