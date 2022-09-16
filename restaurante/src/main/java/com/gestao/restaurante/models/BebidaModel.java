package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Component
@Table(name = "bebida")
@Getter
@Setter
public class BebidaModel extends ProdutoModel implements Serializable {

    @Column(nullable = true, length = 3)
    protected int estoque;

    @Column(nullable = true, length = 2)
    protected int limite_minimo;
    
    @Column(nullable = false, length = 10)
    protected Date validade;
    
    public BebidaModel() {
        super();
    }

    protected BebidaModel(int id, CategoryModel categoria, String descricao, double preco, String foto,
                          Date validade, int estoque, int limite_minimo) {
        super();
        this.id = id;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.foto = foto;
        this.validade = validade;
        this.estoque = estoque;
        this.limite_minimo = limite_minimo;
    }

    @Override
    public void registarProduto() {
        // TODO Auto-generated method stub

    }

    @Override
    public void actualizarDados() {
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminarProduto() {
        // TODO Auto-generated method stub

    }

}