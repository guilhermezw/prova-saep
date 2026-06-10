package com.spring.provasaep.senai.dto.movimentacao;

import com.spring.provasaep.senai.dto.UsuarioResponseDTO;
import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoResponseDTO {

    private Long id;
    private Long produtoId;
    private ProdutoResponseDTO produto;
    private UsuarioResponseDTO usuario;
    private TipoMovimentacao tipoMovimentacao;
    private int quantidade;
    private String motivo;
    private LocalDateTime dataMovimentacao;

    public MovimentacaoResponseDTO(Long id, Long produtoId, ProdutoResponseDTO produto, UsuarioResponseDTO usuario, TipoMovimentacao tipoMovimentacao, int quantidade, String motivo, LocalDateTime dataMovimentacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.produto = produto;
        this.usuario = usuario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.motivo = motivo;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public ProdutoResponseDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponseDTO produto) {
        this.produto = produto;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
