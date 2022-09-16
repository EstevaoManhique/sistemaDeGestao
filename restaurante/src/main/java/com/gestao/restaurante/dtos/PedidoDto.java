package com.gestao.restaurante.dtos;

import java.util.List;

import com.gestao.restaurante.models.Item_Pedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {

    private Long id;

    private int idCliente;

    private int idBalconista;

    private String observacoes;

    private String data;

    private String estado;

    protected List<Item_Pedido> items_Pedidos;
}