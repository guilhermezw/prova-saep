package com.spring.provasaep.senai.mapper;

import com.spring.provasaep.senai.dto.movimentacao.MovimentacaoResponseDTO;
import com.spring.provasaep.senai.model.MovimentacaoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentacaoMapper {

    MovimentacaoResponseDTO toResponseDto(MovimentacaoModel movimentacao);
}
