package com.spring.provasaep.senai.service;

import com.spring.provasaep.senai.exception.custom.UsuarioNaoAutenticadoException;
import com.spring.provasaep.senai.model.UsuarioModel;
import com.spring.provasaep.senai.repository.UsuarioRepository;
import com.spring.provasaep.senai.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel getUsuarioAutenticado(){
        UUID id = SecurityUtils.getUsuarioId();
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoAutenticadoException("Usuário autenticado não encontrado"));
    }
}
