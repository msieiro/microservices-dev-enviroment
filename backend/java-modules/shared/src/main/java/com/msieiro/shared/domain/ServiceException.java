package com.msieiro.shared.domain;

public class ServiceException extends Exception {
    public ServiceException(String errorMsg) {
        super(errorMsg);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
