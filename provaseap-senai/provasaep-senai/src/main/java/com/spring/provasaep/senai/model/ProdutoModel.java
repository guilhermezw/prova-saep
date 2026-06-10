package com.spring.provasaep.senai.model;

import com.spring.provasaep.senai.enums.ProdutoStatus;
import com.spring.provasaep.senai.enums.ProdutoTipo;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "equipamentos")
@EntityListeners(AuditingEntityListener.class)
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProdutoTipo Tipo;

    @Column(unique = true, nullable = false)
    private String codigoPatrimonio;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProdutoStatus status;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private int quantidadeMinima;

    @CreatedDate
    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    private String observacoes;

    public ProdutoModel() {
    }

    public ProdutoModel(Long id, String nome, ProdutoTipo tipo, String codigoPatrimonio, String descricao, ProdutoStatus status, int quantidade, int quantidadeMinima, LocalDateTime dataRegistro, String observacoes) {
        this.id = id;
        this.nome = nome;
        Tipo = tipo;
        this.codigoPatrimonio = codigoPatrimonio;
        this.descricao = descricao;
        this.status = status;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.dataRegistro = dataRegistro;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProdutoTipo getTipo() {
        return Tipo;
    }

    public void setTipo(ProdutoTipo tipo) {
        Tipo = tipo;
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

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
