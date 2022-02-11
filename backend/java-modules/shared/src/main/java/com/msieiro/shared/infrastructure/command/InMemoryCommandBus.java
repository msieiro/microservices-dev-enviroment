package com.msieiro.shared.infrastructure.command;

import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.command.Command;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.command.CommandHandler;
import com.msieiro.shared.domain.bus.command.CommandHandlerExecutionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Service
public final class InMemoryCommandBus implements CommandBus {

    @Autowired
    private CommandHandlersInformation information;
    @Autowired
    private ApplicationContext context;

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());
            CommandHandler handler = context.getBean(commandHandlerClass);
            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }

    @Override
    public void dispatches(List<Command> commands) throws CommandHandlerExecutionError {
        commands.forEach(command -> {
            this.dispatch(command);
        });
    }
}
