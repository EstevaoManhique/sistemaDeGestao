package com.gestao.email.controllers;

import com.gestao.email.dtos.EmailDto;
import com.gestao.email.models.EmailModel;
import com.gestao.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public Object sendEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        return emailService.sendEmail(emailModel);
    }
}
