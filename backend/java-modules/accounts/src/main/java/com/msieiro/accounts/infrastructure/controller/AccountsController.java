package com.msieiro.accounts.infrastructure.controller;

import com.msieiro.accounts.application.AccountRegistrationRequest;
import com.msieiro.accounts.application.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public record AccountsController(AccountsService accountsService) {
    @PostMapping
    public ResponseEntity<?> registerAccount(@RequestBody AccountRegistrationRequest accountRegistrationRequest) {
        log.info("new AccountRegistrationRequest {}", accountRegistrationRequest);
        accountsService.registerAccount(accountRegistrationRequest);
        log.info("new account registration {}", accountRegistrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
