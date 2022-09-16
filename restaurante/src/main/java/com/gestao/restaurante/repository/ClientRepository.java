package com.gestao.restaurante.repository;

import com.gestao.restaurante.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClienteModel, UUID> {

}
