package com.msieiro.shared.infrastructure.query;

import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Service
public final class InMemoryQueryBus implements QueryBus {

    @Autowired
    private QueryHandlersInformation information;
    @Autowired
    private ApplicationContext context;

    @Override
    public Response ask(Query query) throws QueryHandlerExecutionError {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());

            QueryHandler handler = context.getBean(queryHandlerClass);

            return handler.handle(query);
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}
