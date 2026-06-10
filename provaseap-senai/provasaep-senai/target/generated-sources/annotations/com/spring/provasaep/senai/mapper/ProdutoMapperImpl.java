package com.spring.provasaep.senai.mapper;

import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import com.spring.provasaep.senai.model.ProdutoModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-10T20:24:51-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (Microsoft)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public ProdutoResponseDTO toResponseDto(ProdutoModel produto) {
        if ( produto == null ) {
            return null;
        }

        String nome = null;
        ProdutoTipo tipo = null;
        String codigoPatrimonio = null;
        String descricao = null;
        ProdutoStatus status = null;
        int quantidade = 0;
        int quantidadeMinima = 0;
        String observacoes = null;

        nome = produto.getNome();
        tipo = produto.getTipo();
        codigoPatrimonio = produto.getCodigoPatrimonio();
        descricao = produto.getDescricao();
        status = produto.getStatus();
        quantidade = produto.getQuantidade();
        quantidadeMinima = produto.getQuantidadeMinima();
        observacoes = produto.getObservacoes();

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO( nome, tipo, codigoPatrimonio, descricao, status, quantidade, quantidadeMinima, observacoes );

        return produtoResponseDTO;
    }
}
