package com.msieiro.shared.domain.bus.event;

import com.msieiro.shared.domain.Utils;

import java.io.Serializable;
import java.util.HashMap;

public final class DomainEventJsonSerializer {
    public static String serialize(DomainEvent domainEvent) {
        HashMap<String, Serializable> attributes = domainEvent.toPrimitives();
        attributes.put("id", domainEvent.getAggregateId());

        return Utils.jsonEncode(new HashMap<String, Serializable>() {
            {
                put("data", new HashMap<String, Serializable>() {
                    {
                        put("id", domainEvent.getEventId());
                        put("type", domainEvent.eventName());
                        put("occurred_on", domainEvent.getOccurredOn());
                        put("attributes", attributes);
                    }
                });
                put("meta", new HashMap<String, Serializable>());
            }
        });
    }
}
