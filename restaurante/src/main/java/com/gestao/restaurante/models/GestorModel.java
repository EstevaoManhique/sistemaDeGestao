package com.gestao.restaurante.models;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Gestor")
@Component
public class GestorModel extends UsuarioModel implements Serializable {

    public GestorModel() {
        super();
    }

    public GestorModel(String outrosNomes, String email, String nome, String senha, String estado) {
        super();
        this.email = email;
        this.nome = nome;
        this.outrosNomes = outrosNomes;
        this.senha = senha;
        this.estado = estado;
    }


    @Override
    public void actualizarDadosDoUsuario() {

    }

    @Override
    public void desativarUsuario() {

    }
}
