package com.msieiro.shared.domain.bus.event;

import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Service
public final class DomainEventJsonDeserializer {

    @Autowired
    private DomainEventsInformation information;

    public DomainEvent deserialize(String body)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        HashMap<String, Serializable> eventData = Utils.jsonDecode(body);
        HashMap<String, Serializable> data = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes = (HashMap<String, Serializable>) data.get("attributes");
        Class<? extends DomainEvent> domainEventClass = information.forName((String) data.get("type"));

        DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

        Method fromPrimitivesMethod = domainEventClass.getMethod(
            "fromPrimitives",
            String.class,
            HashMap.class,
            String.class,
            String.class);

        Object domainEvent = fromPrimitivesMethod.invoke(
            nullInstance,
            attributes.get("id"),
            attributes,
            data.get("id"),
            data.get("occurred_on"));

        return (DomainEvent) domainEvent;
    }
}
