package com.spring.provasaep.senai.mapper;

import com.spring.provasaep.senai.dto.UsuarioResponseDTO;
import com.spring.provasaep.senai.dto.historico.HistoricoResponseDTO;
import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import com.spring.provasaep.senai.enums.Role;
import com.spring.provasaep.senai.enums.TipoAcao;
import com.spring.provasaep.senai.model.HistoricoModel;
import com.spring.provasaep.senai.model.ProdutoModel;
import com.spring.provasaep.senai.model.UsuarioModel;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-10T21:06:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (Microsoft)"
)
@Component
public class HistoricoMapperImpl implements HistoricoMapper {

    @Override
    public HistoricoResponseDTO toResponseDto(HistoricoModel historico) {
        if ( historico == null ) {
            return null;
        }

        Long id = null;
        ProdutoResponseDTO produto = null;
        UsuarioResponseDTO usuario = null;
        TipoAcao tipoAcao = null;
        String campoAlterado = null;
        String valorAntigo = null;
        String valorNovo = null;
        LocalDateTime dataAcao = null;

        id = historico.getId();
        produto = produtoModelToProdutoResponseDTO( historico.getProduto() );
        usuario = usuarioModelToUsuarioResponseDTO( historico.getUsuario() );
        tipoAcao = historico.getTipoAcao();
        campoAlterado = historico.getCampoAlterado();
        valorAntigo = historico.getValorAntigo();
        valorNovo = historico.getValorNovo();
        dataAcao = historico.getDataAcao();

        HistoricoResponseDTO historicoResponseDTO = new HistoricoResponseDTO( id, produto, usuario, tipoAcao, campoAlterado, valorAntigo, valorNovo, dataAcao );

        return historicoResponseDTO;
    }

    protected ProdutoResponseDTO produtoModelToProdutoResponseDTO(ProdutoModel produtoModel) {
        if ( produtoModel == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        ProdutoTipo tipo = null;
        String codigoPatrimonio = null;
        String descricao = null;
        ProdutoStatus status = null;
        int quantidade = 0;
        int quantidadeMinima = 0;
        String observacoes = null;

        id = produtoModel.getId();
        nome = produtoModel.getNome();
        tipo = produtoModel.getTipo();
        codigoPatrimonio = produtoModel.getCodigoPatrimonio();
        descricao = produtoModel.getDescricao();
        status = produtoModel.getStatus();
        quantidade = produtoModel.getQuantidade();
        quantidadeMinima = produtoModel.getQuantidadeMinima();
        observacoes = produtoModel.getObservacoes();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO( id, nome, tipo, codigoPatrimonio, descricao, status, quantidade, quantidadeMinima, observacoes );

        return produtoResponseDTO;
    }

    protected UsuarioResponseDTO usuarioModelToUsuarioResponseDTO(UsuarioModel usuarioModel) {
        if ( usuarioModel == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        String email = null;
        Role role = null;

        id = usuarioModel.getId();
        nome = usuarioModel.getNome();
        email = usuarioModel.getEmail();
        role = usuarioModel.getRole();

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( id, nome, email, role );

        return usuarioResponseDTO;
    }
}
