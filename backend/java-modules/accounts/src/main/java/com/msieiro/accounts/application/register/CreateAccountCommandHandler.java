package com.msieiro.accounts.application.register;

import com.msieiro.accounts.domain.*;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.command.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    @Autowired
    private AccountCreator creator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void handle(CreateAccountCommand command) {
        log.info("Ha llegado un command al commandHandler: {}", command.toString());
        AccountId id = new AccountId(command.id());
        AccountEmail email = new AccountEmail(command.email());
        AccountPassword password = new AccountPassword(passwordEncoder.encode(command.password()));
        creator.createAccount(id, email, password);
    }
}
