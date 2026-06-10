package com.spring.provasaep.senai.security;

import com.spring.provasaep.senai.exception.custom.UsuarioNaoAutenticadoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getUsuarioId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof Jwt jwt){
            String usuarioId = jwt.getSubject();
            return UUID.fromString(usuarioId);
        }
        throw new UsuarioNaoAutenticadoException ("Usuário não autenticado");
    }
}
