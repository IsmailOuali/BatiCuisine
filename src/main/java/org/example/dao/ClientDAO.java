package org.example.dao;

import java.util.List;
import org.example.models.Client;

public interface ClientDAO {
    void addClient(Client client);
    Client getClientById(int id);
    List<Client> getAllClients();
    void updateClient(Client client);
    void deleteClient(int id);
}
