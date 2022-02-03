package com.msieiro.accounts.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
public class AccountEmail implements Serializable {
    @Email
    protected String email;

    public AccountEmail() {
        this.email = null;
    }

    public AccountEmail(String email) {
        this.email = email;
    }
}
