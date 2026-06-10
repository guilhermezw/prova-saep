package com.spring.provasaep.senai.dto.movimentacao;

import com.spring.provasaep.senai.enums.TipoMovimentacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovimentacaoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "O tipo de movimentação (ENTRADA ou SAIDA) é obrigatório.")
    private TipoMovimentacao tipoMovimentacao;

    @Min(value = 1, message = "A quantidade movimentada deve ser de no mínimo 1 item.")
    private int quantidade;

    @Size(max = 255, message = "A justificativa/motivo não pode ultrapassar 255 caracteres.")
    private String motivo;

    public MovimentacaoRequestDTO(Long produtoId, TipoMovimentacao tipoMovimentacao, int quantidade, String motivo) {
        this.produtoId = produtoId;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.motivo = motivo;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
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
}