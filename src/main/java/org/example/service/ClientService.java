package org.example.service;


import org.example.models.Client;
import org.example.repository.ClientRepository;

import java.sql.Connection;
import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(Connection connection) {
        this.clientRepository = new ClientRepository(connection);
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

