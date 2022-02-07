package com.msieiro.shared.domain;

public abstract class DomainError extends RuntimeException {
    public DomainError(String errorMessage) {
        super(errorMessage);
    }
}
