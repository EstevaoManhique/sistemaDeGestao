package com.gestao.restaurante.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteDto {

    @NotBlank
    private int id_Dispositivo;

    private String nome;

    @NotBlank
    private String id_Mesa;

}