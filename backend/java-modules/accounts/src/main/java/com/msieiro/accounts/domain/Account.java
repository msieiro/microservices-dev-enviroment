package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.AggregateRoot;
import com.msieiro.shared.domain.bus.event.accounts.CreatedAccountDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@Entity
public final class Account extends AggregateRoot {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public Account(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Account create(String id, String firstName, String lastName, String email) {
        Account account = new Account(id, firstName, lastName, email);
        account.record(new CreatedAccountDomainEvent(account.getId(), account.getFirstName(), account.getLastName(), account.getEmail()));
        return account;
    }
}
