package com.gestao.restaurante.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name = "categoria_produtos")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CategoriaProdutosModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 255, unique = true)
    private String descricao;

    @OneToMany
    @JoinColumn(name = "id_produto")
    private List<CategoryModel> categorias;


}