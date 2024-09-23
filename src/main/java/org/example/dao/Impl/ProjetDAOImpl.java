package org.example.dao.Impl;

import org.example.dao.ProjetDAO;
import org.example.models.Projet;
import org.example.models.EtatProjet;

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
        String query = "INSERT INTO Projet (nomProjet, margeBeneficiaire, coutTotal, etatProjet, client_id) VALUES (?, ?, ?, ?::etatprojet_enum, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getMargeBeneficiaire());
            stmt.setDouble(3, projet.getCoutTotal());
            stmt.setString(4, EtatProjet.EN_COURS.name());
            stmt.setInt(5, projet.getClientId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Projet getProjetById(int id) throws SQLException {
        String query = "SELECT * FROM Projet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Projet(
                        rs.getString("nomProjet"),
                        rs.getDouble("margeBeneficiaire"),
                        rs.getDouble("coutTotal"),
                        EtatProjet.valueOf(rs.getString("etatProjet")),
                        rs.getInt("client_id")
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
                projets.add(new Projet(
                        rs.getString("nomProjet"),
                        rs.getDouble("margeBeneficiaire"),
                        rs.getDouble("coutTotal"),
                        EtatProjet.valueOf(rs.getString("etatProjet")),
                        rs.getInt("client_id")
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
