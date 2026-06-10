package com.spring.provasaep.senai.enums;

public enum Role {
    ALMOXARIFADO("Almoxarifado");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
