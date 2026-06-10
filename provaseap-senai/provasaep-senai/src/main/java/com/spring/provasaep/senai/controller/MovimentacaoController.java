package com.spring.provasaep.senai.controller;

import com.spring.provasaep.senai.dto.movimentacao.MovimentacaoRequestDTO;
import com.spring.provasaep.senai.dto.movimentacao.MovimentacaoResponseDTO;
import com.spring.provasaep.senai.enums.TipoMovimentacao;
import com.spring.provasaep.senai.model.MovimentacaoModel;
import com.spring.provasaep.senai.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movimentacoes")
@Tag(name = "Movimentações de Estoque", description = "Endpoints para registrar e auditar entradas e saídas de equipamentos")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    @Operation(summary = "Registrar nova movimentação", description = "Realiza a entrada ou saída de um produto no estoque. O saldo do equipamento é atualizado automaticamente e a ação é vinculada ao usuário autenticado.")
    public ResponseEntity<Map<String, Object>> registrarMovimentacao(@Valid @RequestBody MovimentacaoRequestDTO dto) {
       movimentacaoService.registrarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Movimentação registrada com sucesso", "success", true));
    }

    @GetMapping("/filtro")
    @Operation(
            summary = "Filtrar histórico de movimentações",
            description = "Retorna o histórico paginado aplicando filtros dinâmicos cruzados por tipo, ID do produto, nome do produto ou nome do usuário."
    )
    public ResponseEntity<Page<MovimentacaoResponseDTO>> filtrar(
            @Parameter(description = "Tipo de movimentação (ENTRADA ou SAIDA)") @RequestParam(required = false) TipoMovimentacao tipo,
            @Parameter(description = "ID exato do produto movimentado") @RequestParam(required = false) Long produtoId,
            @Parameter(description = "Nome parcial do produto movimentado") @RequestParam(required = false) String produtoNome,
            @Parameter(description = "Nome parcial do usuário que realizou a ação") @RequestParam(required = false) String usuarioNome,
            @ParameterObject @PageableDefault(size = 10, sort = "dataMovimentacao", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MovimentacaoResponseDTO> resultado = movimentacaoService.filtrarHistorico(tipo, produtoId, produtoNome, usuarioNome, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping
    @Operation(summary = "Auditoria de Estoque (Histórico)", description = "Retorna o histórico paginado de todas as entradas e saídas, ordenado por padrão da movimentação mais recente para a mais antiga.")
    public ResponseEntity<Page<MovimentacaoResponseDTO>> listarHistorico(@ParameterObject @PageableDefault(size = 10, sort = "dataMovimentacao", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.listarTodas(pageable));
    }
}