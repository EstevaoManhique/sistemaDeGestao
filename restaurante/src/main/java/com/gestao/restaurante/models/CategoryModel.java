package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@Entity
@Component
@Getter
@Setter
@Table(name = "categoria")
@JsonIgnoreProperties()
public class CategoryModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 30)
    private String descricao;

    private String descricaoCategoriaProdutos;

    @OneToMany
    @JoinColumn(name = "categoria")
    private List<RefeicaoModel> refeicoes;
    
    @OneToMany
    @JoinColumn(name = "categoria")
    private List<BebidaModel> bebidas;
    
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private CategoriaProdutosModel categoriaProdutos;
    
    public CategoryModel() {
        super();
        //TODO Auto-generated constructor stub
    }

    public CategoryModel(String descricao, Long id, String descricaoCategoriaProdutos) {
        super();
        this.descricao = descricao;
        this.id = id;
        this.descricaoCategoriaProdutos = descricaoCategoriaProdutos;
    }

    public void registarCategoria(){

    }

    public void actualizarCategoria(){

    }

    public void apagarCategoria(){

    }

}
