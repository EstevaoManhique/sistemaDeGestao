package com.gestao.restaurante.services;

import com.gestao.restaurante.models.UsuarioModel;
import com.gestao.restaurante.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UsuarioModel save (UsuarioModel usuarioModel){
        return userRepository.save(usuarioModel);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public Optional<UsuarioModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UsuarioModel> findByEmailAndSenha(String email, String senha) {
        return userRepository.findByEmailAndSenha(email, senha);
    }

    public List<UsuarioModel> findAll() {
        return userRepository.findAll();
    }
    public void delete(UsuarioModel usuarioModel) {
        userRepository.delete(usuarioModel);
    }

    public Optional<UsuarioModel> findBySenha(String senha) {
        return userRepository.findBySenha(senha);
    }
}