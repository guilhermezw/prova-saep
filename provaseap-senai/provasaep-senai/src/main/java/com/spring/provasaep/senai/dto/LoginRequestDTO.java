package com.spring.provasaep.senai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @Email(message = "O e-mail fornecido não é um endereço válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    public LoginRequestDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public @Email(message = "O e-mail fornecido não é um endereço válido.") @NotBlank(message = "O e-mail é obrigatório.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "O e-mail fornecido não é um endereço válido.") @NotBlank(message = "O e-mail é obrigatório.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória.") String senha) {
        this.senha = senha;
    }
}