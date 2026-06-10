package com.spring.provasaep.senai.dto.historico;

import com.spring.provasaep.senai.dto.UsuarioResponseDTO;
import com.spring.provasaep.senai.dto.produto.ProdutoResponseDTO;
import com.spring.provasaep.senai.enums.TipoAcao;

import java.time.LocalDateTime;

public class HistoricoResponseDTO {

    private Long id;
    private ProdutoResponseDTO produto;
    private UsuarioResponseDTO usuario;
    private TipoAcao tipoAcao;
    private String campoAlterado;
    private String valorAntigo;
    private String valorNovo;
    private LocalDateTime dataAcao;

    public HistoricoResponseDTO(Long id, ProdutoResponseDTO produto, UsuarioResponseDTO usuario, TipoAcao tipoAcao, String campoAlterado, String valorAntigo, String valorNovo, LocalDateTime dataAcao) {
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
