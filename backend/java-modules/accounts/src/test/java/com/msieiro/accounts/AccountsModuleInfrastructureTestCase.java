package com.msieiro.accounts;

import com.msieiro.accounts.domain.AccountRepository;
import com.msieiro.accounts.infrastructure.persistence.InMemoryAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccountsModuleInfrastructureTestCase extends AccountsContextInfrastructureTestCase {
    protected InMemoryAccountsRepository inMemoryCourseRepository = new InMemoryAccountsRepository();
    @Autowired
    protected AccountRepository        accountRepository;
}
