package com.spring.provasaep.senai.dto.produto;


import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do equipamento é obrigatório.")
    @Size(max = 100, message = "O nome não pode passar de 100 caracteres.")
    private String nome;

    @NotNull(message = "O tipo de produto é obrigatório.")
    private ProdutoTipo tipo;

    @NotBlank(message = "O código de patrimônio é obrigatório.")
    private String codigoPatrimonio;

    private String descricao;

    @NotNull(message = "O status inicial é obrigatório.")
    private ProdutoStatus status;

    @Min(value = 0, message = "A quantidade não pode ser negativa.")
    private int quantidade;

    @Min(value = 0, message = "A quantidade mínima não pode ser negativa.")
    private int quantidadeMinima;

    private String observacoes;

    public ProdutoRequestDTO(String nome, ProdutoTipo tipo, String codigoPatrimonio, String descricao, ProdutoStatus status, int quantidade, int quantidadeMinima, String observacoes) {
        this.nome = nome;
        this.tipo = tipo;
        this.codigoPatrimonio = codigoPatrimonio;
        this.descricao = descricao;
        this.status = status;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProdutoTipo getTipo() {
        return tipo;
    }

    public void setTipo(ProdutoTipo tipo) {
        this.tipo = tipo;
    }

    public String getCodigoPatrimonio() {
        return codigoPatrimonio;
    }

    public void setCodigoPatrimonio(String codigoPatrimonio) {
        this.codigoPatrimonio = codigoPatrimonio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProdutoStatus getStatus() {
        return status;
    }

    public void setStatus(ProdutoStatus status) {
        this.status = status;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}