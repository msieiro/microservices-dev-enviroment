package com.msieiro.accounts.application.login;

import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.accounts.domain.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
@Slf4j
public class AccountAuthenticator implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(new AccountEmail(email)).map(account -> {
            log.info("Loaded user {} with authority {}", email, account.getRole().name());
            GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().name());
            return new org.springframework.security.core.userdetails.User(email, account.getPassword().getPassword(),
                Collections.singleton(authority));
        }).orElseThrow(() -> new UsernameNotFoundException("Account: " + email + " not found"));
    }
}
