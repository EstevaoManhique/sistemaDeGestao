package com.gestao.restaurante.services;

import com.gestao.restaurante.repository.BebidaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    BebidaRepository BebidaRepository;

    public ProductService(BebidaRepository BebidaRepository){
        this.BebidaRepository = BebidaRepository;
    }
}