package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.AggregateRoot;
import com.msieiro.shared.domain.bus.event.accounts.CreatedAccountDomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public final class Account extends AggregateRoot implements Serializable {

    @EmbeddedId
    private AccountId id;
    @Embedded
    private AccountLogin login;
    private String firstName;
    private String lastName;

    public Account(String id, String email, String password) {
        this.id = new AccountId(id);
        this.login = AccountLogin.create(email, password);
    }

    public Account(AccountId id, AccountLogin login) {
        this.id = id;
        this.login = login;
    }

    public static Account create(String id, String email, String password) {
        Account account = new Account(id, email, password);
        account.record(new CreatedAccountDomainEvent(id, email, password));
        return account;
    }
}
