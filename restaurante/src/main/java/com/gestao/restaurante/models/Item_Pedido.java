package com.gestao.restaurante.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class Item_Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoModel id_pedido;

    @ManyToOne
    @JoinColumn(name = "id_bebida")
    private BebidaModel id_bebida;

    @ManyToOne
    @JoinColumn(name = "id_refeicao")
    private RefeicaoModel id_refeicao;

    int quantidade;
}