package com.msieiro.accounts.infrastructure.persistence;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
    extends JpaRepository<Account, AccountId> {
}
