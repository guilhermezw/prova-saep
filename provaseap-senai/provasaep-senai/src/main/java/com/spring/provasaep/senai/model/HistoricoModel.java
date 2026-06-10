package com.spring.provasaep.senai.model;

import com.spring.provasaep.senai.enums.TipoAcao;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_auditoria")
@EntityListeners(AuditingEntityListener.class)
public class HistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = true)
    private ProdutoModel produto;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_acao")
    private TipoAcao tipoAcao;


    @Column(nullable = false)
    private String campoAlterado;

    @Column(columnDefinition = "TEXT")
    private String valorAntigo;

    @Column(columnDefinition = "TEXT")
    private String valorNovo;

    @CreatedDate
    @Column(name = "data_acao", nullable = false)
    private LocalDateTime dataAcao;

    public HistoricoModel() {
    }

    public HistoricoModel(Long id, ProdutoModel produto, UsuarioModel usuario, TipoAcao tipoAcao, String campoAlterado, String valorAntigo, String valorNovo, LocalDateTime dataAcao) {
        this.id = id;
        this.produto = produto;
        this.usuario = usuario;
        this.tipoAcao = tipoAcao;
        this.campoAlterado = campoAlterado;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.dataAcao = dataAcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public TipoAcao getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(TipoAcao tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getCampoAlterado() {
        return campoAlterado;
    }

    public void setCampoAlterado(String campoAlterado) {
        this.campoAlterado = campoAlterado;
    }

    public String getValorAntigo() {
        return valorAntigo;
    }

    public void setValorAntigo(String valorAntigo) {
        this.valorAntigo = valorAntigo;
    }

    public String getValorNovo() {
        return valorNovo;
    }

    public void setValorNovo(String valorNovo) {
        this.valorNovo = valorNovo;
    }

    public LocalDateTime getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(LocalDateTime dataAcao) {
        this.dataAcao = dataAcao;
    }
}