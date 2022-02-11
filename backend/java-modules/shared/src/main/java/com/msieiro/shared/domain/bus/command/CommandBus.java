package com.msieiro.shared.domain.bus.command;

import java.util.List;

public interface CommandBus {
    void dispatch(Command command) throws CommandHandlerExecutionError;
    void dispatches(List<Command> commands) throws CommandHandlerExecutionError;
}
