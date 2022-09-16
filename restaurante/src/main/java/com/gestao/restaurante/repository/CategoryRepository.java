package com.gestao.restaurante.repository;

import com.gestao.restaurante.dtos.CategoryDto;
import com.gestao.restaurante.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
    boolean existsByDescricao (String descricao);
    Optional<CategoryModel> findByDescricao(String descricao);

    @Query("SELECT new com.gestao.restaurante.dtos.CategoryDto (c.id, c.descricao, cp.descricao, cp.id)"
              +"FROM CategoryModel c JOIN c.categoriaProdutos cp where cp.descricao = 'Bebida'")
    public List<CategoryDto> getAll();

    @Query("SELECT new com.gestao.restaurante.dtos.CategoryDto (c.id, c.descricao, cp.descricao, cp.id)"
              +"FROM CategoryModel c JOIN c.categoriaProdutos cp where c.descricao = :descricao")
    public Optional<CategoryDto> getOne(@Param("descricao") String descricao);
}
