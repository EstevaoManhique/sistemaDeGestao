package com.gestao.restaurante.models;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Balconista")
@Component
public class BalconistaModel extends UsuarioModel implements Serializable{

    public BalconistaModel() {
        super();
    }

    public BalconistaModel(String outrosNomes, String email, String nome, String senha, String estado) {
        super();
        this.outrosNomes = outrosNomes;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.estado = estado;
    }

    @Override
    public void actualizarDadosDoUsuario() {
        // TODO Auto-generated method stub

    }

    @Override
    public void desativarUsuario() {
        // TODO Auto-generated method stub

    }

}