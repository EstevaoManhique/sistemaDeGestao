package com.gestao.restaurante.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.restaurante.dtos.CategoriaProdutosDto;

import com.gestao.restaurante.services.CategoriaProdutosService;


@RestController
@CrossOrigin
@RequestMapping("/categoriaprodutos")
public class CategoriaProdutosController {
    
    @Autowired
    private final CategoriaProdutosService categoriaProdutosService;

    public CategoriaProdutosController(CategoriaProdutosService categoriaProdutosService){
        this.categoriaProdutosService = categoriaProdutosService;
    }

    @GetMapping
    public List<CategoriaProdutosDto> getAll(){
        return this.categoriaProdutosService.getAll();
    }
}
