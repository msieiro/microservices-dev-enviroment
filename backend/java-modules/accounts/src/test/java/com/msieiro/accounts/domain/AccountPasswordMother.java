package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.WordMother;

public final class AccountPasswordMother {
    public static AccountPassword create(String value) {
        return new AccountPassword(value);
    }

    public static AccountPassword random() {
        return create(WordMother.random() + WordMother.random() + WordMother.random());
    }
}
