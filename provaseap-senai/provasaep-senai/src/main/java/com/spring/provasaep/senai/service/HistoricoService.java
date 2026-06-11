package com.spring.provasaep.senai.service;

import com.spring.provasaep.senai.dto.historico.HistoricoResponseDTO;
import com.spring.provasaep.senai.enums.TipoAcao;
import com.spring.provasaep.senai.mapper.HistoricoMapper;
import com.spring.provasaep.senai.model.HistoricoModel;
import com.spring.provasaep.senai.model.ProdutoModel;
import com.spring.provasaep.senai.model.UsuarioModel;
import com.spring.provasaep.senai.repository.HistoricoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final HistoricoMapper historicoMapper;
    private final AutenticacaoService autenticacaoService;

    public HistoricoService(HistoricoRepository historicoRepository, HistoricoMapper historicoMapper, AutenticacaoService autenticacaoService) {
        this.historicoRepository = historicoRepository;
        this.historicoMapper = historicoMapper;
        this.autenticacaoService = autenticacaoService;
    }

    /**
     * Registra uma nova ação de auditoria no sistema.
     * Este método deve ser chamado por outros services (ex: ProdutoService)
     * sempre que um cadastro for criado, atualizado ou excluído.
     */
    @Transactional
    public void registrarAcao(ProdutoModel produto, TipoAcao tipoAcao, String campoAlterado, String valorAntigo, String valorNovo) {

        // Captura quem está fazendo a alteração no momento da requisição
        UsuarioModel usuario = autenticacaoService.getUsuarioAutenticado();

        HistoricoModel historico = new HistoricoModel();

        // O ID é gerado automaticamente pelo banco
        // A dataAcao é gerada automaticamente pelo @CreatedDate da sua Model

        historico.setProduto(produto);
        historico.setUsuario(usuario);
        historico.setTipoAcao(tipoAcao);
        historico.setCampoAlterado(campoAlterado);
        historico.setValorAntigo(valorAntigo);
        historico.setValorNovo(valorNovo);

        historicoRepository.save(historico);
    }

    /**
     * Lista o histórico completo de auditoria para exibição no Front-end.
     */
    public Page<HistoricoResponseDTO> listarHistorico(Pageable pageable) {
        return historicoRepository.findAll(pageable)
                .map(historicoMapper::toResponseDto);
    }
}