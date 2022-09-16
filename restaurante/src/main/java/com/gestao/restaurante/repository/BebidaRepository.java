package com.gestao.restaurante.repository;

import com.gestao.restaurante.dtos.BebidaDto;
import com.gestao.restaurante.models.BebidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BebidaRepository extends JpaRepository<BebidaModel, UUID> {
    boolean existsByDescricao(String descricao);
    Optional<BebidaModel> findByDescricao(String descricao);

    @Query("SELECT new com.gestao.restaurante.dtos.BebidaDto (c.id, c.descricao, b.id,"
    +"b.descricao, b.preco, b.foto, b.estoque, b.limite_minimo, b.validade) FROM BebidaModel b JOIN b.categoria c")
    public List<BebidaDto> getAll();

    @Query("SELECT new com.gestao.restaurante.dtos.BebidaDto (c.id, c.descricao, b.id,"
     +"b.descricao, b.preco, b.foto, b.estoque, b.limite_minimo, b.validade) FROM BebidaModel b JOIN b.categoria c where b.descricao = :descricao")
    public Optional<BebidaDto> getOne(@Param("descricao") String descricao);
}
