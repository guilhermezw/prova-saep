package com.spring.provasaep.senai.exception.custom;

public class UsuarioNaoAutenticadoException extends RuntimeException {
    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }
}
