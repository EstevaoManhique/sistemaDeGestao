package com.gestao.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.restaurante.models.Item_Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<Item_Pedido, Long> {
    
}
