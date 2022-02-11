package com.msieiro.accounts.application.exists_account;

import com.msieiro.accounts.domain.Account;
import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.accounts.domain.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExistsAccountChecker {

    @Autowired
    private AccountRepository accountRepository;

    public boolean accountExist(AccountEmail email) {
        Account account = accountRepository.findByEmail(email).orElse(null);
        if (null == account) return false;
        return true;
    }
}
