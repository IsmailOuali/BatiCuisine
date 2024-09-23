package org.example.repository;


import org.example.dao.Impl.ClientDAOImpl;
import org.example.models.Client;
import org.example.dao.ClientDAO;
import org.example.repository.Interface.ClientRepositoryInterface;

import java.sql.Connection;
import java.util.List;

public class ClientRepository   {
    private  ClientDAO clientDAO;

    public ClientRepository(Connection connection) {
        this.clientDAO = new ClientDAOImpl(connection);
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

