package com.gestao.restaurante.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Component
@Data
@Table(name = "pedido")
public class PedidoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int idCliente;
    @Column(nullable = false)
    private int idBalconista;
    private String observacoes;
    @Column(nullable = false)
    private String data;
    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "id_pedido")
    protected List<Item_Pedido> items_Pedidos;

    public PedidoModel() {
        super();
    }

    public PedidoModel(Long id, int idCliente, int idBalconista, String observacoes, String data, String hora,
                          String estado) {
        super();
        this.id = id;
        this.idCliente = idCliente;
        this.idBalconista = idBalconista;
        this.observacoes = observacoes;
        this.data = data;
        this.estado = estado;
    }

    public void actualizarEstado(PedidoModel $pedidoModel) {

    }

    public void registarPagamento() {

    }

    public void encerarPedido() {

    }

    public void imprimirRecido() {

    }
}