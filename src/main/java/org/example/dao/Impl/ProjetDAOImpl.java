package org.example.dao.Impl;

import org.example.dao.ProjetDAO;
import org.example.models.Client;
import org.example.models.Projet;
import org.example.models.EtatProjet;
import org.example.service.ClientService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAOImpl implements ProjetDAO {
    private Connection conn = null;

    public ProjetDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO Projet (nomProjet, margeBeneficiaire, coutTotal, etatProjet, client_id) VALUES (?, ?, ?, ?::etatprojet_enum, ?) RETURNING id";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getMargeBeneficiaire());
            stmt.setDouble(3, projet.getCoutTotal());
            stmt.setString(4, EtatProjet.EN_COURS.name());
            stmt.setInt(5, projet.getClientId());

            // Use executeUpdate to execute the insert
            int affectedRows = stmt.executeUpdate();

            // Now retrieve the generated keys
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        projet.setId(generatedId);
                        System.out.println("Generated project ID in Java: " + projet.getId());  // Debugging output
                    } else {
                        System.out.println("Erreur: Aucun ID généré pour le projet.");
                    }
                }
            } else {
                System.out.println("Erreur: Aucune ligne affectée par l'insertion.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Projet getProjetById(int id) throws SQLException {
        String query = "SELECT * FROM Projet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idClient = rs.getInt("idClient");

                ClientService clientService = new ClientService(conn);
                Client client = clientService.getClient(idClient);
                return new Projet(
                        rs.getString("nomProjet"),
                        rs.getDouble("margeBeneficiaire"),
                        rs.getDouble("coutTotal"),
                        EtatProjet.valueOf(rs.getString("etatProjet")),
                        client
                );
            }
        }
        return null;
    }

    @Override
    public List<Projet> getAllProjets() throws SQLException {
        String query = "SELECT * FROM Projet";
        List<Projet> projets = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idClient = rs.getInt("idClient");

                ClientService clientService = new ClientService(conn);
                Client client = clientService.getClient(idClient);
                projets.add(new Projet(
                        rs.getString("nomProjet"),
                        rs.getDouble("margeBeneficiaire"),
                        rs.getDouble("coutTotal"),
                        EtatProjet.valueOf(rs.getString("etatProjet")),
                        client
                ));
            }
        }
        return projets;
    }

    @Override
    public void updateProjet(Projet projet) throws SQLException {
        String query = "UPDATE Projet SET nomProjet = ?, margeBeneficiaire = ?, coutTotal = ?, etatProjet = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getMargeBeneficiaire());
            stmt.setDouble(3, projet.getCoutTotal());
            stmt.setString(4, projet.getEtatProjet().name());
            stmt.setInt(5, projet.getClientId());
            stmt.setInt(6, projet.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProjet(int id) throws SQLException {
        String query = "DELETE FROM Projet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
