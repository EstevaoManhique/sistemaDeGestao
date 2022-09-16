package com.gestao.email.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailDto {

    @NotBlank
    private String ownerRef = "Recursos Humanos";
    @NotBlank
    @Email
    private String emailFrom = "caracalmanhique@gmail.com";
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject = "Confirmacao da conta";
    @NotBlank
    private String text;
}
