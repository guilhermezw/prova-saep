package com.spring.provasaep.senai.repository;

import com.spring.provasaep.senai.model.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel , Long> {
}
