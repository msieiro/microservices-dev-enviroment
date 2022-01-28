package com.msieiro.shared.domain.bus.query;

public final class QueryHandlerExecutionError extends RuntimeException {
    public QueryHandlerExecutionError(String message) {
        super(message);
    }
}
