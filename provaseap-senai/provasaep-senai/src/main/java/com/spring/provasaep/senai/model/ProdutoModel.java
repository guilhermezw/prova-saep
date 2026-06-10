package com.spring.provasaep.senai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
public class ProdutoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    
}
