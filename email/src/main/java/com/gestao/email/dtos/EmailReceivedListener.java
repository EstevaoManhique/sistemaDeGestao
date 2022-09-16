package com.gestao.email.dtos;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestao.email.models.EmailModel;
import com.gestao.email.services.EmailService;

@Component
public class EmailReceivedListener {

    @Autowired
    EmailService emailService = new EmailService();

    @RabbitListener(queues = "registro.balconista.envio-email")
    public void onOrderCreated(EnvioEmailDto event) {
        EmailModel emailModel = new EmailModel();

        emailModel.setEmailTo(event.getEmail());
        emailModel.setText("Senha de autenticacao: 7804");

        emailService.sendEmail(emailModel);
    }

}