package com.gestao.restaurante.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioEmailDto {
    
    private String email;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String senha;
}
