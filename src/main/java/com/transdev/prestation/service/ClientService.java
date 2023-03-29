package com.transdev.prestation.service;

import com.transdev.prestation.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<Client> getAllClients();

    Client getClientById(Long clientId);

    void addNewClient(Client client);
}
