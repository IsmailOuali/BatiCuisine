package org.example.repository;


import org.example.models.Client;
import org.example.dao.ClientDAO;
import org.example.repository.Interface.ClientRepositoryInterface;

import java.util.List;

public class ClientRepository   {
    private  ClientDAO clientDAO;

    public ClientRepository(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }




    public void save(Client client) {
        clientDAO.addClient(client);
    }

    public Client findById(int id) {
        return clientDAO.getClientById(id);
    }

    public List<Client> findAll() {
        return clientDAO.getAllClients();
    }

    public void update(Client client) {
        clientDAO.updateClient(client);
    }

    public void deleteById(int id) {
        clientDAO.deleteClient(id);
    }
}

