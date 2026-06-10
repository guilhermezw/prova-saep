package com.spring.provasaep.senai.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum TipoAcao {
    INSERCAO("Inserção"),
    ALTERACAO("Alteração"),
    EXCLUSAO("Exclusão");

    private final String value;

    TipoAcao(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoAcao fromValue(String value) {
        for (TipoAcao tipoAcao : TipoAcao.values()) {
            if (tipoAcao.value.equalsIgnoreCase(value)) {
                return tipoAcao;
            }
        }
        try {
            return TipoAcao.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Valor de ação inválido: " + value);
        }
    }

    @Component
    public static class StringTipoAcaoConverter implements Converter<String, TipoAcao> {
        @Override
        public TipoAcao convert(String value) {
            return TipoAcao.fromValue(value);
        }
    }
}