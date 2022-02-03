package com.msieiro.shared.infrastructure.exception_handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
final class ErrorResponse {
    private HashMap<String, String> errorz;

    public ErrorResponse(String field, String error) {
        this.errorz = new HashMap<>();
        errorz.put(field, error);
    }

    public void add(String field, String error) {
        errorz.put(field, error);
    }
}
