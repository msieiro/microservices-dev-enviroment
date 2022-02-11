package com.msieiro.accounts.infrastructure.persistence;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.accounts.domain.AccountId;
import com.msieiro.accounts.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepositoryJPA
    extends JpaRepository<Account, AccountId>, AccountRepository  {

}
