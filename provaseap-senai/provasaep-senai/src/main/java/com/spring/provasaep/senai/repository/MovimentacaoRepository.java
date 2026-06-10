package com.spring.provasaep.senai.repository;

import com.spring.provasaep.senai.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel , Long> , JpaSpecificationExecutor<MovimentacaoModel> {
}
