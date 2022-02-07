package com.msieiro.shared.infrastructure.controller;

import com.msieiro.shared.domain.bus.command.Command;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.command.CommandHandlerExecutionError;
import com.msieiro.shared.domain.bus.query.Query;
import com.msieiro.shared.domain.bus.query.QueryBus;
import com.msieiro.shared.domain.bus.query.QueryHandlerExecutionError;

public abstract class ApiController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    public ApiController(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus   = queryBus;
        this.commandBus = commandBus;
    }

    protected void dispatch(Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }

    protected <R> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }
}
