package com.msieiro.accounts.domain;

import com.msieiro.accounts.application.create.CreateAccountCommand;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class AccountMother {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Account create(AccountId id, AccountEmail email, AccountPassword password) {
        return new Account(id, email, password);
    }

    public static Account fromRequest(CreateAccountCommand request) {
        return create(
            AccountIdMother.create(request.id()),
            AccountEmailMother.create(request.email()),
            AccountPasswordMother.create(request.password())
        );
    }

    public static Account random() {
        return create(AccountIdMother.random(), AccountEmailMother.random(), AccountPasswordMother.random());
    }
}
