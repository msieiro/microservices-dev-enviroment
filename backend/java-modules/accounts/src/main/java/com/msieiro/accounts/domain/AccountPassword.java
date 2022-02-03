package com.msieiro.accounts.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@ToString
public class AccountPassword implements Serializable {

    @Min(value = 4)
    @Max(value = 23)
    protected String password;

    public AccountPassword() {
        this.password = null;
    }

    public AccountPassword(String password) {
        this.password = password;
    }
}
