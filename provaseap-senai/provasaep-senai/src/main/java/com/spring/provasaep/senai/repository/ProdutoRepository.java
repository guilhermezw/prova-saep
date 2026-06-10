package com.spring.provasaep.senai.repository;

import com.spring.provasaep.senai.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel , Long> , JpaSpecificationExecutor<ProdutoModel> {

    Optional<ProdutoModel> findByCodigoPatrimonio(String codigoPatrimonio);
}
