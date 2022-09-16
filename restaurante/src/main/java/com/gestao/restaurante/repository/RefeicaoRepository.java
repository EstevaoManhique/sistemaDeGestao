package com.gestao.restaurante.repository;

import com.gestao.restaurante.dtos.RefeicaoDto;
import com.gestao.restaurante.models.RefeicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefeicaoRepository extends JpaRepository<RefeicaoModel, UUID> {
    boolean existsByDescricao(String descricao);
    Optional<RefeicaoModel> findByDescricao(String descricao);

    @Query("SELECT new com.gestao.restaurante.dtos.RefeicaoDto (c.id, c.descricao, r.id, r.descricao, r.preco, r.foto) FROM RefeicaoModel r JOIN r.categoria c")
    public List<RefeicaoDto> getAll();

    @Query("SELECT new com.gestao.restaurante.dtos.RefeicaoDto (c.id, c.descricao, r.id,"
     +"r.descricao, r.preco, r.foto) FROM RefeicaoModel r JOIN r.categoria c where r.descricao = :descricao")
    public Optional<RefeicaoDto> getOne(@Param("descricao") String descricao);
}