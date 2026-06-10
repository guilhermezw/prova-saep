package com.spring.provasaep.senai.service;

import com.spring.provasaep.senai.dto.produto.ProdutoRequestDTO;
import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import com.spring.provasaep.senai.enums.TipoAcao;
import com.spring.provasaep.senai.exception.custom.ConflitoDeDadosException;
import com.spring.provasaep.senai.exception.custom.RecursoNaoEncontradoException;
import com.spring.provasaep.senai.mapper.ProdutoMapper;
import com.spring.provasaep.senai.model.ProdutoModel;
import com.spring.provasaep.senai.repository.ProdutoRepository;
import com.spring.provasaep.senai.repository.specs.ProdutoSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final HistoricoService historicoService;

    // Injeção do HistoricoService adicionada ao construtor
    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoMapper produtoMapper,
                          HistoricoService historicoService) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.historicoService = historicoService;
    }

    public Page<ProdutoResponseDTO> filtrarProdutos(String nome, String codigoPatrimonio, ProdutoStatus status, ProdutoTipo tipo, Pageable pageable) {
        Specification<ProdutoModel> spec = Specification.allOf(
                ProdutoSpecs.comNome(nome),
                ProdutoSpecs.comCodigoPatrimonio(codigoPatrimonio),
                ProdutoSpecs.comStatus(status),
                ProdutoSpecs.comTipo(tipo)
        );

        return produtoRepository
                .findAll(spec, pageable)
                .map(produtoMapper::toResponseDto);
    }

    public Page<ProdutoResponseDTO> listarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(produtoMapper::toResponseDto);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Equipamento não encontrado com o ID: " + id));
        return produtoMapper.toResponseDto(produto);
    }

    @Transactional
    public ProdutoModel salvar(ProdutoRequestDTO dto) {
        Optional<ProdutoModel> produtoExistente = produtoRepository.findByCodigoPatrimonio(dto.getCodigoPatrimonio());
        if (produtoExistente.isPresent()) {
            throw new ConflitoDeDadosException("Já existe um equipamento cadastrado com este código de patrimônio!");
        }

        ProdutoModel produto = new ProdutoModel();
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setCodigoPatrimonio(dto.getCodigoPatrimonio());
        produto.setDescricao(dto.getDescricao());
        produto.setQuantidade(dto.getQuantidade());
        produto.setQuantidadeMinima(dto.getQuantidadeMinima());
        produto.setObservacoes(dto.getObservacoes());
        produto.setStatus(dto.getStatus());

        verificarAlertaEstoque(produto);

        // Salvamos primeiro para o banco gerar o ID
        ProdutoModel produtoSalvo = produtoRepository.save(produto);

        // Registramos a criação no histórico
        historicoService.registrarAcao(produtoSalvo, TipoAcao.INSERCAO, "Cadastro Inicial", "N/A", "Equipamento Cadastrado");

        return produtoSalvo;
    }

    @Transactional
    public ProdutoModel atualizar(Long id, ProdutoRequestDTO dto) {
        ProdutoModel produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Equipamento não encontrado"));

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setTipo(dto.getTipo());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setQuantidade(dto.getQuantidade());
        produtoExistente.setQuantidadeMinima(dto.getQuantidadeMinima());
        produtoExistente.setObservacoes(dto.getObservacoes());
        produtoExistente.setStatus(dto.getStatus());

        verificarAlertaEstoque(produtoExistente);

        ProdutoModel produtoAtualizado = produtoRepository.save(produtoExistente);

        // Registramos a atualização no histórico
        historicoService.registrarAcao(produtoAtualizado, TipoAcao.ALTERACAO, "Dados Gerais", "Dados Anteriores Substituídos", "Dados Atualizados");

        return produtoAtualizado;
    }

    @Transactional
    public void deletar(Long id) {
        // Precisamos buscar o produto antes de deletar para ter os dados para salvar no histórico
        ProdutoModel produtoParaDeletar = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não é possível deletar. Equipamento não encontrado."));

        produtoRepository.deleteById(id);

        // Registramos a exclusão passando o objeto que acabou de ser deletado
        historicoService.registrarAcao(produtoParaDeletar, TipoAcao.EXCLUSAO, "Registro Apagado", produtoParaDeletar.getNome(), "N/A");
    }

    private void verificarAlertaEstoque(ProdutoModel produto) {
        if (produto.getQuantidade() <= produto.getQuantidadeMinima()) {
            produto.setStatus(ProdutoStatus.ALERTA_ESTOQUE);
        } else if (produto.getStatus() == ProdutoStatus.ALERTA_ESTOQUE) {
            // Se a quantidade subiu e estava em alerta, volta a ficar disponível
            produto.setStatus(ProdutoStatus.DISPONIVEL);
        }
    }
}