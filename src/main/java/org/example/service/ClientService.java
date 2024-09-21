package org.example.service;


import org.example.models.Client;
import org.example.repository.ClientRepository;

import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public Client getClient(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}

