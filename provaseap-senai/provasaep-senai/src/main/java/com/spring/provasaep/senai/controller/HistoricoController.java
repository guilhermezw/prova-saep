package com.spring.provasaep.senai.controller;

import com.spring.provasaep.senai.dto.historico.HistoricoResponseDTO;
import com.spring.provasaep.senai.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico")
@Tag(name = "Auditoria de Sistema (Histórico)", description = "Endpoint exclusivo para leitura do histórico de alterações de produtos (criação, edição e exclusão).")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping
    @Operation(summary = "Listar histórico de auditoria", description = "Retorna uma página contendo os registros de auditoria do sistema. A lista é ordenada por padrão da ação mais recente para a mais antiga.")
    public ResponseEntity<Page<HistoricoResponseDTO>> listarHistorico(@ParameterObject @PageableDefault(size = 10, sort = "dataAcao", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(historicoService.listarHistorico(pageable));
    }
}