package com.msieiro.accounts.infrastructure.persistence;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.accounts.domain.AccountRepository;

import java.util.HashMap;
import java.util.Optional;

public final class InMemoryAccountsRepository implements AccountRepository {

    private HashMap<String, Account> accounts = new HashMap<>();

    public Account save(Account account) {
        return accounts.put(account.getId().getAccountId(), account);
    }

    @Override
    public Optional<Account> findByEmail(final AccountEmail email) {
        return Optional.ofNullable(accounts.get(email));
    }
}
