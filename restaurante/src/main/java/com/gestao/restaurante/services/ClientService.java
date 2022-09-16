package com.gestao.restaurante.services;

import com.gestao.restaurante.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
}
