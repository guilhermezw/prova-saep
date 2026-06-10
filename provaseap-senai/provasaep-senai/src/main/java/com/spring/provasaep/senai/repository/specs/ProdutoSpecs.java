package com.spring.provasaep.senai.repository.specs;

import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import com.spring.provasaep.senai.model.ProdutoModel;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecs {


    public static Specification<ProdutoModel> comNome(String nome) {
        return (root, query, builder) ->
                nome == null ? null : builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<ProdutoModel> comCodigoPatrimonio(String codigoPatrimonio) {
        return (root, query, builder) ->
                codigoPatrimonio == null ? null : builder.like(builder.lower(root.get("codigoPatrimonio")), "%" + codigoPatrimonio.toLowerCase() + "%");
    }

    public static Specification<ProdutoModel> comStatus(ProdutoStatus status) {
        return (root, query, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<ProdutoModel> comTipo(ProdutoTipo tipo) {
        return (root, query, builder) ->
                tipo == null ? null : builder.equal(root.get("tipo"), tipo);
    }
}