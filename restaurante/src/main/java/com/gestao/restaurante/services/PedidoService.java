package com.gestao.restaurante.services;

import com.gestao.restaurante.models.PedidoModel;
import com.gestao.restaurante.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;

    public PedidoService (PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoModel save(PedidoModel pedidoModel){
       return pedidoRepository.save(pedidoModel);
    }

    public Optional<PedidoModel> findById(Long id){
        return pedidoRepository.findById(id);
    }

    public boolean existsById(Long id){
        return pedidoRepository.existsById(id);
    }

    public Object findAll() {
        return pedidoRepository.findAll();
    }

    public void delete(PedidoModel pedidoModel) {
        pedidoRepository.delete(pedidoModel);
    }
}
