package com.spring.provasaep.senai.service;

import com.spring.provasaep.senai.dto.movimentacao.MovimentacaoRequestDTO;
import com.spring.provasaep.senai.dto.movimentacao.MovimentacaoResponseDTO;
import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.TipoAcao;
import com.spring.provasaep.senai.enums.TipoMovimentacao;
import com.spring.provasaep.senai.exception.custom.RecursoNaoEncontradoException;
import com.spring.provasaep.senai.exception.custom.RegraNegocioException;
import com.spring.provasaep.senai.mapper.MovimentacaoMapper;
import com.spring.provasaep.senai.model.MovimentacaoModel;
import com.spring.provasaep.senai.model.ProdutoModel;
import com.spring.provasaep.senai.model.UsuarioModel;
import com.spring.provasaep.senai.repository.MovimentacaoRepository;
import com.spring.provasaep.senai.repository.ProdutoRepository;
import com.spring.provasaep.senai.repository.specs.MovimentacaoSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoMapper movimentacaoMapper;
    private final AutenticacaoService autenticacaoService;
    private final HistoricoService historicoService; // 1. Injeção do serviço de auditoria

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository, MovimentacaoMapper movimentacaoMapper, AutenticacaoService autenticacaoService, HistoricoService historicoService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
        this.autenticacaoService = autenticacaoService;
        this.historicoService = historicoService;
    }

    @Transactional
    public MovimentacaoModel registrarMovimentacao(MovimentacaoRequestDTO dto) {

        // Obtém o usuário logado de forma segura pelo contexto de segurança
        UsuarioModel usuario = autenticacaoService.getUsuarioAutenticado();

        // Valida se o produto existe no banco de dados
        ProdutoModel produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        // Valida se a quantidade informada na requisição faz sentido
        if (dto.getQuantidade() <= 0) {
            throw new RegraNegocioException("A quantidade movimentada deve ser maior que zero.");
        }

        // Guarda a quantidade antiga para o histórico de auditoria antes de alterar
        String quantidadeAntiga = String.valueOf(produto.getQuantidade());

        // Aplica a regra de negócio baseada no tipo de fluxo (ENTRADA ou SAÍDA)
        if (dto.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            executarEntrada(produto, dto.getQuantidade());
        } else if (dto.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            executarSaida(produto, dto.getQuantidade());
        }

        // Mapeamento manual dos dados do DTO para a Entidade (Model)
        MovimentacaoModel movimentacao = new MovimentacaoModel();
        movimentacao.setTipoMovimentacao(dto.getTipoMovimentacao());
        movimentacao.setQuantidade(dto.getQuantidade());
        movimentacao.setDataMovimentacao(dto.getDataMovimentacao());
        movimentacao.setMotivo(dto.getMotivo());

        // Vincula as entidades mapeadas e validadas ao histórico
        movimentacao.setProduto(produto);
        movimentacao.setUsuario(usuario);

        // Salva o registro da movimentação e atualiza o saldo do produto no estoque
        MovimentacaoModel movimentacaoSalva = movimentacaoRepository.save(movimentacao);
        ProdutoModel produtoAtualizado = produtoRepository.save(produto);

        // 2. Registra de forma automática na tabela de auditoria a alteração do estoque
        historicoService.registrarAcao(
                produtoAtualizado,
                TipoAcao.INSERCAO,
                "Estoque (Movimentação de " + dto.getTipoMovimentacao() + ")",
                "Qtd Anterior: " + quantidadeAntiga,
                "Qtd Nova: " + produtoAtualizado.getQuantidade() + " | Motivo: " + dto.getMotivo()
        );

        // Retorna a própria entidade salva
        return movimentacaoSalva;
    }

    // 2. Listar histórico de movimentações de forma paginada (Auditoria)
    public Page<MovimentacaoResponseDTO> listarTodas(Pageable pageable) {
        return movimentacaoRepository.findAll(pageable)
                .map(movimentacaoMapper::toResponseDto);
    }

    // 3. Filtro dinâmico
    public Page<MovimentacaoResponseDTO> filtrarHistorico(TipoMovimentacao tipo, Long produtoId, String produtoNome, String usuarioNome, Pageable pageable) {
        Specification<MovimentacaoModel> spec = Specification.allOf(
                MovimentacaoSpecs.comTipoMovimentacao(tipo),
                MovimentacaoSpecs.comProdutoId(produtoId),
                MovimentacaoSpecs.comProdutoNome(produtoNome),
                MovimentacaoSpecs.comUsuarioNome(usuarioNome)
        );

        return movimentacaoRepository.findAll(spec, pageable)
                .map(movimentacaoMapper::toResponseDto);
    }

    // --- REGRAS DE NEGÓCIO INTERNAS ---

    private void executarEntrada(ProdutoModel produto, int quantidade) {
        // Soma a quantidade movimentada ao estoque atual do produto
        produto.setQuantidade(produto.getQuantidade() + quantidade);

        // Verifica se a nova quantidade remove o produto do estado de Alerta de Estoque
        verificarAlertaEstoque(produto);
    }

    private void executarSaida(ProdutoModel produto, int quantidade) {
        // Bloqueia a saída caso falte saldo para concluir a operação
        if (produto.getQuantidade() < quantidade) {
            throw new RegraNegocioException("Saldo insuficiente em estoque! Quantidade disponível: " + produto.getQuantidade());
        }

        // Subtrai a quantidade movimentada do estoque atual do produto
        produto.setQuantidade(produto.getQuantidade() - quantidade);

        // Verifica se a quantidade restante atingiu o limite de estoque mínimo do produto
        verificarAlertaEstoque(produto);
    }

    private void verificarAlertaEstoque(ProdutoModel produto) {
        if (produto.getQuantidade() <= produto.getQuantidadeMinima()) {
            produto.setStatus(ProdutoStatus.ALERTA_ESTOQUE);
        } else if (produto.getStatus() == ProdutoStatus.ALERTA_ESTOQUE) {
            produto.setStatus(ProdutoStatus.DISPONIVEL);
        }
    }
}