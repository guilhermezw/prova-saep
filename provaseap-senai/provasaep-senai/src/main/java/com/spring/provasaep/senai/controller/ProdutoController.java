package com.spring.provasaep.senai.controller;

import com.spring.provasaep.senai.dto.produto.ProdutoRequestDTO;
import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import com.spring.provasaep.senai.service.ProdutoService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos e Equipamentos", description = "Endpoints para gerenciamento do inventário de ferramentas")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/filtro")
    @Operation(summary = "Filtrar produtos de forma paginada", description = "Retorna uma página de produtos com base nos critérios de pesquisa informados.")
    public ResponseEntity<Page<ProdutoResponseDTO>> filtrar(
            @Parameter(description = "Nome parcial do equipamento") @RequestParam(required = false) String nome,
            @Parameter(description = "Código de patrimônio exato ou parcial") @RequestParam(required = false) String codigoPatrimonio,
            @Parameter(description = "Status atual do equipamento") @RequestParam(required = false) ProdutoStatus status,
            @Parameter(description = "Tipo/categoria do equipamento") @RequestParam(required = false) ProdutoTipo tipo,
            @ParameterObject @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<ProdutoResponseDTO> resultado = produtoService.filtrarProdutos(nome, codigoPatrimonio, status, tipo, pageable);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista completa com todos os equipamentos cadastrados no sistema (sem paginação).")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarTodos(@ParameterObject @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar equipamento por ID", description = "Retorna os detalhes de um único produto com base no ID fornecido.")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@Parameter(description = "ID do produto", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo equipamento", description = "Realiza a inserção de uma nova ferramenta no inventário. O código de patrimônio deve ser exclusivo.")
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ProdutoRequestDTO dto) { // Correção: Fechamento do '>' aplicado
        produtoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Produto criado", "success", true));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um equipamento existente", description = "Modifica os dados cadastrais de um produto ativo a partir do seu ID.")
    public ResponseEntity<Map<String, Object>> atualizar(@Parameter(description = "ID do produto", required = true) @PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto) {
        produtoService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Produto atualizado", "success", true)); // Correção: Mensagem alterada para refletir a atualização
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um equipamento", description = "Remove definitivamente um produto do banco de dados com base em seu ID.")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do produto", required = true) @PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}