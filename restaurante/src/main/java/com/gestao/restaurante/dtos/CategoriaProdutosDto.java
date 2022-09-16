package com.gestao.restaurante.dtos;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class CategoriaProdutosDto {
    
    private int id;
    private String descricao;

}
