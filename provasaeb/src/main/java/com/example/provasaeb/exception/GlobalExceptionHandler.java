package com.example.provasaeb.exception;


import com.example.provasaeb.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta handlerMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        List<ErroCampo> errors = error.getFieldErrors()
                .stream()
                .map(e -> new ErroCampo(e.getField(), e.getDefaultMessage()))
                .toList();

        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "Erro de validação nos campos informados.",
                errors
        );

    }


    @ExceptionHandler({
            AcessoNegadoException.class,
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroResposta handlerForbiddenException(RuntimeException erro){
        return ErroResposta.resposta(HttpStatus.FORBIDDEN , erro.getMessage());
    }

    @ExceptionHandler({
            RecursoNaoEncontradoException.class,
            UsuarioNaoEncontradoException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handlerNotFoundException(RuntimeException erro){
        return ErroResposta.resposta(HttpStatus.NOT_FOUND , erro.getMessage());
    }

    @ExceptionHandler({
            RegraNegocioException.class,
            ErroException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerBadRequestException(RuntimeException erro) {
        return ErroResposta.resposta(HttpStatus.BAD_REQUEST , erro.getMessage());
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerTypeMismatch(MethodArgumentTypeMismatchException erro) {

        String mensagem = String.format("O valor '%s' é inválido.", erro.getValue());

        if(erro.getRequiredType() != null && erro.getRequiredType().isEnum()){
            mensagem += "Os valores aceitos são: " + Arrays.toString(erro.getRequiredType().getEnumConstants());
        }

        return ErroResposta.resposta(HttpStatus.BAD_REQUEST , mensagem);
    }


    @ExceptionHandler({
            ConflitoDeDadosException.class,
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handlerConflictException(RuntimeException erro) {
        return ErroResposta.resposta(HttpStatus.CONFLICT , erro.getMessage());
    }


    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta handlerCampoInvalidoException(CampoInvalidoException erro) {
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "Validação falhou para o campo informado.",
                List.of(new ErroCampo(erro.getCampo(), erro.getMessage()))
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handlerErroInterno(Exception ex) {
        return new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado no servidor. Contate o suporte.",
                List.of()
        );
    }


}