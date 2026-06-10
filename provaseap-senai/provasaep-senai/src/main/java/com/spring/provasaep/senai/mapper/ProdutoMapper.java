package com.spring.provasaep.senai.mapper;

import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.model.ProdutoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponseDTO toResponseDto(ProdutoModel produto);
}
