package com.msieiro.accounts.application.exists_account;

import com.msieiro.shared.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class ExistsAccountResponse implements Response {
    private boolean existingAccount;
}
