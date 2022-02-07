package com.msieiro.accounts.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository
    extends JpaRepository<Account, AccountId> {
    Optional<Account> findByEmail(AccountEmail email);
}
