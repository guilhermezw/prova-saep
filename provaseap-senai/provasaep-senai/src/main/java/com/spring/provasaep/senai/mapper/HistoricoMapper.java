package com.spring.provasaep.senai.mapper;

import com.spring.provasaep.senai.dto.historico.HistoricoResponseDTO;
import com.spring.provasaep.senai.model.HistoricoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoricoMapper {

    HistoricoResponseDTO toResponseDto(HistoricoModel historico);
}
