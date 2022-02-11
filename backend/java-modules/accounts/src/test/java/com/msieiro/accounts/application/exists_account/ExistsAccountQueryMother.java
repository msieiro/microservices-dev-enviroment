package com.msieiro.accounts.application.exists_account;

import com.msieiro.shared.domain.WordMother;

public final class ExistsAccountQueryMother {
    public static ExistsAccountQuery create(String email) {
        return new ExistsAccountQuery(email);
    }
    public static ExistsAccountQuery random() {
        return create(WordMother.random());
    }
}
