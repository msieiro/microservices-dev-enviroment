package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.UuidMother;

public final class AccountIdMother {
    public static AccountId create(String value) {
        return new AccountId(value);
    }

    public static AccountId random() {
        return create(UuidMother.random());
    }
}
