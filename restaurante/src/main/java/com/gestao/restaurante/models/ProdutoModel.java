package com.gestao.restaurante.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoria")
    protected CategoryModel categoria;

    @OneToMany(mappedBy = "id_bebida")
    protected List<Item_Pedido> item_Pedidos;

    @Column(nullable = false, unique = true, length = 30)
    protected String descricao;

    @Column(nullable = false, length = 4)
    protected double preco;

    protected String foto;

    public abstract void registarProduto();
    public abstract void actualizarDados();
    public abstract void eliminarProduto();

}