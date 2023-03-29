package com.transdev.prestation.service;

import com.transdev.prestation.model.Client;
import com.transdev.prestation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.getReferenceById(clientId);
    }

    @Override
    public void addNewClient(Client client) {
        clientRepository.save(client);
    }


}
