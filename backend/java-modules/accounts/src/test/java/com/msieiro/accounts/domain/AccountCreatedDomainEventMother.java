package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.bus.event.accounts.AccountCreatedDomainEvent;

public final class AccountCreatedDomainEventMother {
    public static AccountCreatedDomainEvent create(AccountId id, AccountEmail email, AccountPassword password) {
        return new AccountCreatedDomainEvent(id.getAccountId(), email.getEmail(), password.getPassword());
    }

    public static AccountCreatedDomainEvent fromAccount(Account account) {
        return create(account.getId(), account.getEmail(), account.getPassword());
    }

    public static AccountCreatedDomainEvent fromAccountWithEvent(Account account) {
        return (AccountCreatedDomainEvent) account.pullDomainEvents().get(0);
    }

    public static AccountCreatedDomainEvent random() {
        return create(AccountIdMother.random(), AccountEmailMother.random(), AccountPasswordMother.random());
    }
}
