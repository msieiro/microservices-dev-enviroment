package com.msieiro.accounts.infrastructure.controller;

import com.msieiro.accounts.domain.WrongCredentials;
import com.msieiro.shared.domain.bus.command.CommandBus;
import com.msieiro.shared.domain.bus.command.CommandHandlerExecutionError;
import com.msieiro.shared.domain.bus.query.QueryBus;
import com.msieiro.shared.infrastructure.controller.ApiController;
import com.msieiro.shared.infrastructure.security.JWTConfigurer;
import com.msieiro.shared.infrastructure.security.JWTToken;
import com.msieiro.shared.infrastructure.security.TokenProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class LoginAccountController extends ApiController {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginAccountController(final QueryBus queryBus, final CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAccount(@Valid @RequestBody LoginAccountRequest request)
        throws CommandHandlerExecutionError, WrongCredentials {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            request.getEmail(), request.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            log.info("JWT TOKEN {}", jwt);
            return new ResponseEntity<JWTToken>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.warn(e.getMessage(), e);
            throw new WrongCredentials(e.getMessage());
        }
    }
}

@Data
final class LoginAccountRequest {

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
