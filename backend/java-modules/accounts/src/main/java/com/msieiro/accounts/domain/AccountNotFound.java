package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.DomainError;

public class AccountNotFound extends DomainError {
    public AccountNotFound(String message){
        super(message);
    }
}
