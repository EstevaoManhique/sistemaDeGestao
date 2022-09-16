package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Component
@Getter
@Setter
@Table(name = "cliente")
public class ClienteModel implements Serializable {

    @Id
    private int id_Dispositivo;
    @Column(length = 30)
    private String nome;
    //@ForeignKey()
    private String id_Mesa;

    protected ClienteModel() {
        super();
    }

    protected ClienteModel(String nome, int id_Dispositivo, String id_Mesa) {
        super();
        this.nome = nome;
        this.id_Dispositivo = id_Dispositivo;
        this.id_Mesa = id_Mesa;
    }

    public void associarAMesa(){

    }

    public void desassociarDaMesa(){

    }

    public void verConta(){

    }

}