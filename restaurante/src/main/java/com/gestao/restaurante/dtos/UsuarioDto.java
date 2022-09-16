package com.gestao.restaurante.dtos;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDto {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
    @NotBlank
    private String outrosNomes;

    @NotBlank
    private String nome;

    private String estado;

}
