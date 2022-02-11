package com.msieiro.accounts.domain;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByEmail(AccountEmail email);
}
