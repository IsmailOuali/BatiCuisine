package org.example.repository.Interface;

import org.example.dao.ClientDAO;
import org.example.models.Client;

import java.util.List;

public interface ClientRepositoryInterface {


    public void ClientRepository(ClientDAO clientDAO);
    void save(Client client);
    public void deleteById(int id);
    public void update(Client client);
    public List<Client> findAll();
    Client getClientByName(String name);
    public Client findById(int id);
}
