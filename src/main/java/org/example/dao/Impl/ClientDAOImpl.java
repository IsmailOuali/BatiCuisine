package org.example.dao.Impl;


import org.example.dao.ClientDAO;
import org.example.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final Connection connection;

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClient(Client client) {
        String query = "INSERT INTO Client (nom, adresse, telephone, estprofessionnel) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstprofessionnel());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                client.setId(generatedId);
                System.out.println("Generated client ID in Java: " + client.getId());  // Debugging output
            } else {
                System.out.println("Erreur: Aucun ID généré pour le client.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client findClientByName(String name) {
        String query = "SELECT * FROM Client WHERE nom = ?";
        Client client = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                boolean estProfessionnel = rs.getBoolean("estProfessionnel");

                client = new Client( name, adresse, telephone, estProfessionnel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return client;
    }

    @Override
    public Client getClientById(int id) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estProfessionnel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        String query = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estprofessionnel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE client SET nom = ?, adresse = ?, telephone = ?, estprofessionnel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstprofessionnel());
            stmt.setInt(5, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        String query = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

