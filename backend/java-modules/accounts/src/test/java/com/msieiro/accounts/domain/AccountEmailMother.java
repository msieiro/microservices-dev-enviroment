package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.WordMother;

public final class AccountEmailMother {
    public static AccountEmail create(String value) {
        return new AccountEmail(value);
    }

    public static AccountEmail random() {
        return create(WordMother.random() + "@" + WordMother.random() + ".com");
    }
}
