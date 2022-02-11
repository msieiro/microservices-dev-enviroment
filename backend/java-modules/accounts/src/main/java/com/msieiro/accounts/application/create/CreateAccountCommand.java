package com.msieiro.accounts.application.create;

import com.msieiro.shared.domain.bus.command.Command;

public record CreateAccountCommand(String id, String email, String password) implements Command {
}
