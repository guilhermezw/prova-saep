package com.spring.provasaep.senai.exception.custom;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

public record ErroResposta (int status, String mensagem, LocalDateTime timestamp, List<ErroCampo> error) {

    public ErroResposta(int status, String mensagem, List<ErroCampo> error) {
        this(status, mensagem, LocalDateTime.now(), error);
    }

    public static ErroResposta resposta(HttpStatus status, String mensagem) {
        return new ErroResposta(status.value(), mensagem, List.of());
    }

}