package com.msieiro.accounts;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountRepository;
import com.msieiro.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class AccountsModuleUnitTestCase extends UnitTestCase {

    protected AccountRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(AccountRepository.class);
    }

    public void shouldHaveSaved(Account account) {
        verify(repository, atLeastOnce()).save(account);
    }
}
