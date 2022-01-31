package com.msieiro.accounts.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AccountLogin implements Serializable {

    private String email;
    private String password;

    public static AccountLogin create(final String email, final String password){
        return new AccountLogin(email, password);
    }
}
