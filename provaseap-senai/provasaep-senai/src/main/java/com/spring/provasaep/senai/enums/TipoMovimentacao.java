package com.spring.provasaep.senai.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum TipoMovimentacao {
    ENTRADA("Entrada"),
    SAIDA("Saida"),
    MANUTENCAO("Manutenção");

    private String value;

    TipoMovimentacao(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoMovimentacao fromValue(String value){
        for(TipoMovimentacao tipoMovimentacao : TipoMovimentacao.values()){
            if(tipoMovimentacao.value.equalsIgnoreCase(value)){
                return tipoMovimentacao;
            }
        }

        try {
            return TipoMovimentacao.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Valor do Tipo inválido " + value);
        }

    }

    @Component
    public static class StringGeneroConverter implements Converter<String , TipoMovimentacao> {
        @Override
        public TipoMovimentacao convert(String value) {
            return TipoMovimentacao.fromValue(value);
        }
    }
}
