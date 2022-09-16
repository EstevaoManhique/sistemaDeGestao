package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Component
@Table(name = "usuario")
@Getter
@Setter
public abstract class UsuarioModel {
    @Id
    @Column(nullable = false, unique = true)
    protected String email;
    @Column(nullable = false, length = 50)
    protected String outrosNomes;
    @Column(nullable = false, length = 15)
    protected String nome;
    @Column(nullable = false, length = 30)
    protected String senha;
    @Column(nullable = false, length = 20)
    protected String estado;
    public UsuarioModel() {
        super();
    }
    public abstract void actualizarDadosDoUsuario();
    public abstract void desativarUsuario();

}
