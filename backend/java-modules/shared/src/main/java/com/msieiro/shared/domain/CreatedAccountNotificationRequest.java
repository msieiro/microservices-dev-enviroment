package com.msieiro.shared.domain;

import java.io.Serializable;

public record CreatedAccountNotificationRequest(String email, String authKey) implements Serializable {
}
