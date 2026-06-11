package com.spring.provasaep.senai.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum ProdutoStatus {
    DISPONIVEL("Disponivel"),
    EMPRESTADO("Emprestado"),
    MANUTENCAO("Manutenção"),
    DANIFICADO("Danificado"),
    BAIXADO("Baixado"),
    ALERTA_ESTOQUE("Alerta Estoque"),
    EXCLUIDO("Excluido");

    private final String value;

    ProdutoStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProdutoStatus fromValue(String value){
        for(ProdutoStatus produtoStatus : ProdutoStatus.values()){
            if(produtoStatus.value.equalsIgnoreCase(value)){
                return produtoStatus;
            }
        }

        try {
            return ProdutoStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Valor do genero inválido " + value);
        }

    }

    @Component
    public static class StringGeneroConverter implements Converter<String , ProdutoStatus> {
        @Override
        public ProdutoStatus convert(String value) {
            return ProdutoStatus.fromValue(value);
        }
    }
}
