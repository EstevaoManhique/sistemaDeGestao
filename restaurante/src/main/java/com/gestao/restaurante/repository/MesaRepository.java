package com.gestao.restaurante.repository;

import com.gestao.restaurante.models.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel, UUID>{
    boolean existsById(Long id);
    boolean existsByQr(String qr_mesa);
    Optional<MesaModel> findById(Long id);

}
