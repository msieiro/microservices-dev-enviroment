package com.msieiro.accounts.application.create;

import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.accounts.domain.AccountId;
import com.msieiro.accounts.domain.AccountPassword;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.command.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    private final AccountCreator creator;

    public CreateAccountCommandHandler(final AccountCreator creator){
        this.creator = creator;
    }

    @Override
    public void handle(CreateAccountCommand command) {
        log.info("Ha llegado un command al commandHandler: {}", command.toString());
        AccountId id = new AccountId(command.id());
        AccountEmail email = new AccountEmail(command.email());
        AccountPassword password = new AccountPassword(command.password());
        creator.createAccount(id, email, password);
    }
}
