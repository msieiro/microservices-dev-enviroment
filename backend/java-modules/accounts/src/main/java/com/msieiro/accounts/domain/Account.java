package com.msieiro.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public final class Account {
    @Id
    @SequenceGenerator(name = "account_id_sequence", sequenceName = "account_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
