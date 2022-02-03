package com.msieiro.accounts.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
    extends JpaRepository<Account, AccountId> {
}
