package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Mesa")
public class MesaModel implements Serializable {

    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String qr;

    private int quantidade;

    public MesaModel() {
        super();
    }

    protected MesaModel(String qr, Long id) {
        super();
        this.qr = qr;
        this.id= id;
    }

    public void adicionarMesas(){

    }

    public void reduzirMesas(){

    }

    public void imprimirMesas(){

    }

    public void imprimirQrDasMesas(){

    }

}