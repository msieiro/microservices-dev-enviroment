package com.msieiro.accounts.application.create;

import com.msieiro.accounts.domain.*;

public final class CreateAccountCommandMother {
    public static CreateAccountCommand create(AccountId id, AccountEmail email, AccountPassword password) {
        return new CreateAccountCommand(id.getAccountId(), email.getEmail(), password.getPassword());
    }

    public static CreateAccountCommand random() {
        return create(AccountIdMother.random(), AccountEmailMother.random(), AccountPasswordMother.random());
    }
}
