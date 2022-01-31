package com.msieiro.accounts.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
public class AccountId implements Serializable {

    protected String accountId;

    public AccountId(String accountId) {
        ensureValidUuid(accountId);
        this.accountId = accountId;
    }

    protected AccountId() {
        this.accountId = null;
    }

    private void ensureValidUuid(String accountId) throws IllegalArgumentException {
        UUID.fromString(accountId);
    }
}
