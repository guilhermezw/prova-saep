package com.spring.provasaep.senai.dto;

import com.spring.provasaep.senai.enums.Role;

import java.util.UUID;

public class PerfilResponseDTO {

    private UUID id;
    private String nome;
    private String email;
    private Role role;

    public PerfilResponseDTO(UUID id, String nome, String email, Role role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
