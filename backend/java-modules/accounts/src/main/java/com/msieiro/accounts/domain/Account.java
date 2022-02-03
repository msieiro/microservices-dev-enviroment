package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.AggregateRoot;
import com.msieiro.shared.domain.bus.event.accounts.CreatedAccountDomainEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Account extends AggregateRoot implements Serializable {

    @EmbeddedId
    private AccountId id;
    @Embedded
    private AccountEmail email;
    @Embedded
    private AccountPassword password;

    public Account(String id, String email, String password) {
        this.id = new AccountId(id);
        this.email = new AccountEmail(email);
        this.password = new AccountPassword(password);
    }

    public static Account create(AccountId id, AccountEmail email, AccountPassword password) {
        Account account = new Account(id, email, password);
        log.info("Creating account by method Account.create : {}", account);
        CreatedAccountDomainEvent event = new CreatedAccountDomainEvent(id.getAccountId(), email.getEmail(),
            password.getPassword());
        log.info("CreatedAccountDomainEvent created by method Account.create : {}", event);
        account.record(event);
        return account;
    }
}
