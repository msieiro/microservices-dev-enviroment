package com.msieiro.accounts.infrastructure.controller;

import com.msieiro.accounts.application.register.CreateAccountCommand;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.command.CommandHandlerExecutionError;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public class CreateAccountController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping
    public ResponseEntity<?> registerAccount(@Valid @RequestBody CreateAccountRequest request)
        throws CommandHandlerExecutionError {
        return createAccount(request);
    }

    private ResponseEntity<?> createAccount(CreateAccountRequest request) throws CommandHandlerExecutionError {
        commandBus.dispatch(new CreateAccountCommand(
            request.getId(),
            request.getEmail(),
            request.getPassword()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

@Data
final class CreateAccountRequest {

    @NotNull
    @NotBlank
    @Size(min = 36, max = 36)
    private String id;

    @Email
    @NotNull
    @NotBlank
    @Size(min = 4, max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 23)
    private String password;
}
