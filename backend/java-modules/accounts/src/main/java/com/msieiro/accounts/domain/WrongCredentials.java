package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.DomainError;

public class WrongCredentials extends DomainError {
    public WrongCredentials(String message) {
        super(message);
    }
}
