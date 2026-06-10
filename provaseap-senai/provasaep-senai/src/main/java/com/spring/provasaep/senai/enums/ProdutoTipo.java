package com.spring.provasaep.senai.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum ProdutoTipo {
    MANUAL("Manual"),
    ELETRICO("Elétrico"),
    PNEUMATICO("Pneumático"),
    MEDICAO("Medição"),
    EPI("EPI"),
    CONSUMIVEL("Consumível");

    private final String value;

    ProdutoTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProdutoTipo fromValue(String value) {
        for (ProdutoTipo produtoTipo : ProdutoTipo.values()) {
            if (produtoTipo.value.equalsIgnoreCase(value)) {
                return produtoTipo;
            }
        }

        try {
            return ProdutoTipo.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Valor de tipo inválido: " + value);
        }
    }

    @Component
    public static class StringProdutoTipoConverter implements Converter<String, ProdutoTipo> {
        @Override
        public ProdutoTipo convert(String value) {
            return ProdutoTipo.fromValue(value);
        }
    }
}