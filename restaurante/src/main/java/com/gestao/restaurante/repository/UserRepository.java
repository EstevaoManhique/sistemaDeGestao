package com.gestao.restaurante.repository;

import com.gestao.restaurante.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsuarioModel, UUID> {
    boolean existsByEmail (String email);
    Optional<UsuarioModel> findByEmail(String email);
    Optional<UsuarioModel> findByEmailAndSenha(String email, String senha);
    Optional<UsuarioModel> findBySenha(String senha);
}
