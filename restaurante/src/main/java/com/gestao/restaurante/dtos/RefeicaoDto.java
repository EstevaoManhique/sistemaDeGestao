package com.gestao.restaurante.dtos;

import com.gestao.restaurante.models.CategoryModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RefeicaoDto {

    private CategoryModel categoria = new CategoryModel();

    private int id;

    private String descricao;

    private double preco;

    private String foto;  

    public RefeicaoDto(Long idCategoria, String descricaoCategoria, int id, String descricao, double preco, String foto){
        this.categoria.setId(idCategoria);
        this.categoria.setDescricao(descricaoCategoria);
        this.setId(id);
        this.setDescricao(descricao);
        this.setPreco(preco);
        this.setFoto(foto);
    }
    
}