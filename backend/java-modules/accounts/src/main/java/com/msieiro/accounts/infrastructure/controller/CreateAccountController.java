package com.msieiro.accounts.infrastructure.controller;

import com.msieiro.accounts.application.exists_account.ExistsAccountQuery;
import com.msieiro.accounts.application.exists_account.ExistsAccountResponse;
import com.msieiro.accounts.application.create.CreateAccountCommand;
import com.msieiro.accounts.domain.WrongCredentials;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.command.CommandHandlerExecutionError;
import com.msieiro.shared.domain.bus.query.QueryBus;
import com.msieiro.shared.infrastructure.controller.ApiController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
public class CreateAccountController extends ApiController {

    public CreateAccountController(final QueryBus queryBus, final CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody CreateAccountRequest request)
        throws CommandHandlerExecutionError, WrongCredentials {
        ExistsAccountResponse response = ask(new ExistsAccountQuery(request.getEmail()));
        if (response.isExistingAccount()) throw new WrongCredentials("Email already registered");
        return createAccount(request);
    }

    private ResponseEntity<?> createAccount(CreateAccountRequest request) throws CommandHandlerExecutionError {
        dispatch(new CreateAccountCommand(
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
