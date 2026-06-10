package com.spring.provasaep.senai.dto.produto;

import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;


public class ProdutoResponseDTO {

    private String nome;
    private ProdutoTipo tipo;
    private String codigoPatrimonio;
    private String descricao;
    private ProdutoStatus status;
    private int quantidade;
    private int quantidadeMinima;
    private String observacoes;

    public ProdutoResponseDTO(String nome, ProdutoTipo tipo, String codigoPatrimonio, String descricao, ProdutoStatus status, int quantidade, int quantidadeMinima, String observacoes) {
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
