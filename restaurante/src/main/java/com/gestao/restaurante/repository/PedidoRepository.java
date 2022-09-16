package com.gestao.restaurante.repository;

import com.gestao.restaurante.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {

    Optional<PedidoModel> findById(Long id);

    boolean existsById(Long id);
}