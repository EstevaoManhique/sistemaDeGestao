package com.gestao.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import com.gestao.restaurante.models.CategoriaProdutosModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CategoryDto {

    private Long id;

    @NotBlank
    private String descricao;

    private CategoriaProdutosModel categoriaProdutos = new CategoriaProdutosModel();

    public CategoryDto(CategoriaProdutosModel categoriaProdutos){
        this.categoriaProdutos = categoriaProdutos;
    }

    public CategoryDto (Long id, String descricao, String descCategoriaProduto, int idCatProd){
        this.categoriaProdutos.setDescricao(descCategoriaProduto);
        this.categoriaProdutos.setId(idCatProd);
        this.descricao = descricao;
        this.id = id;
    }

}