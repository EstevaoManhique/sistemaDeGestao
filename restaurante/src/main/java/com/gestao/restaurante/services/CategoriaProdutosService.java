package com.gestao.restaurante.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.gestao.restaurante.dtos.CategoriaProdutosDto;
import com.gestao.restaurante.repository.CategoriaProdutosRepository;

@Service
public class CategoriaProdutosService {
 
    CategoriaProdutosRepository categoriaProdutosRepository;

    public CategoriaProdutosService(CategoriaProdutosRepository categoriaProdutosRepository){
        this.categoriaProdutosRepository = categoriaProdutosRepository;
    }
    public List<CategoriaProdutosDto> getAll(){
        return categoriaProdutosRepository.getAll();
    }
}
