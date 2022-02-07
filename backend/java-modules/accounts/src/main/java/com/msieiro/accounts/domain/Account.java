package com.msieiro.accounts.domain;

import com.msieiro.shared.domain.AggregateRoot;
import com.msieiro.shared.domain.bus.event.accounts.CreatedAccountDomainEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private AccountId id;

    @Embedded
    @Column(name = "email", unique = true, nullable = false)
    private AccountEmail email;

    @Embedded
    @Column(name = "password", nullable = false)
    private AccountPassword password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private AccountRole role;

    public Account(String id, String email, String password) {
        super();
        this.id = new AccountId(id);
        this.email = new AccountEmail(email);
        this.password = new AccountPassword(password);
        this.role = AccountRole.ROLE_USER;
    }

    public static Account create(AccountId id, AccountEmail email, AccountPassword password) {
        Account account = new Account(id, email, password, AccountRole.ROLE_USER);
        CreatedAccountDomainEvent event = new CreatedAccountDomainEvent(id.getAccountId(), email.getEmail(),
            password.getPassword());
        log.info("CreatedAccountDomainEvent created by method Account.create : {}", event);
        account.record(event);
        return account;
    }
}
