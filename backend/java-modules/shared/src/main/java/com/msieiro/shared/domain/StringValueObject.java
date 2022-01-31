package com.msieiro.shared.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@EqualsAndHashCode
@Data
public abstract class StringValueObject {
    private String value;
    public StringValueObject(String value) {
        this.value = value;
    }
    protected StringValueObject() {
        this.value = null;
    }
}
