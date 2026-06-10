package com.spring.provasaep.senai.repository.specs;

import com.spring.provasaep.senai.enums.TipoMovimentacao;
import com.spring.provasaep.senai.model.MovimentacaoModel;
import org.springframework.data.jpa.domain.Specification;

public class MovimentacaoSpecs {


    public static Specification<MovimentacaoModel> comTipoMovimentacao(TipoMovimentacao tipo) {
        return (root, query, builder) ->
                tipo == null ? null : builder.equal(root.get("tipoMovimentacao"), tipo);
    }


    public static Specification<MovimentacaoModel> comProdutoId(Long produtoId) {
        return (root, query, builder) ->
                produtoId == null ? null : builder.equal(root.get("produto").get("id"), produtoId);
    }


    public static Specification<MovimentacaoModel> comProdutoNome(String produtoNome) {
        return (root, query, builder) ->
                produtoNome == null ? null : builder.like(builder.lower(root.join("produto").get("nome")), "%" + produtoNome.toLowerCase() + "%");
    }


    public static Specification<MovimentacaoModel> comUsuarioNome(String usuarioNome) {
        return (root, query, builder) ->
                usuarioNome == null ? null : builder.like(builder.lower(root.join("usuario").get("nome")), "%" + usuarioNome.toLowerCase() + "%");
    }
}