package com.gestao.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.gestao.restaurante.models.CategoryModel;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BebidaDto {


    private CategoryModel categoria = new CategoryModel();

    private int id;

    private String descricao;

    private double preco;

    private String foto;

    private int estoque;

    private int limite_minimo;

    private Date validade;

    public BebidaDto(Long idCategoria, String descCategoria, int id, String descricao,
        double preco, String foto, int estoque, int limite_minimo, Date validade){
            this.categoria.setDescricao(descCategoria);
            this.categoria.setId(idCategoria);
            this.id = id;
            this.descricao = descricao;
            this.preco = preco;
            this.foto = foto;
            this.estoque = estoque;
            this.limite_minimo = limite_minimo;
            this.validade = validade;
            this.estoque = estoque;
    }
}
