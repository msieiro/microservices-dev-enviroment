package com.msieiro.accounts.application.exists_account;

import com.msieiro.shared.domain.bus.query.Query;

public record ExistsAccountQuery(String email) implements Query {
}
