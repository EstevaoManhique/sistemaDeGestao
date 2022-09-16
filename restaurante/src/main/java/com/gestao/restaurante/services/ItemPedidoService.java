package com.gestao.restaurante.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.restaurante.models.Item_Pedido;
import com.gestao.restaurante.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void save(List<Item_Pedido> itemPedido){
        itemPedidoRepository.saveAll(itemPedido);
    }
    
}