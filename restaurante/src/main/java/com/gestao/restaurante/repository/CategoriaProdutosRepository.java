package com.gestao.restaurante.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestao.restaurante.dtos.CategoriaProdutosDto;
import com.gestao.restaurante.models.CategoriaProdutosModel;

public interface CategoriaProdutosRepository extends JpaRepository<CategoriaProdutosModel, UUID> {
    
    @Query("Select new com.gestao.restaurante.dtos.CategoriaProdutosDto"
    +"(cp.id, cp.descricao) from CategoriaProdutosModel cp")
    public List<CategoriaProdutosDto> getAll();


}
