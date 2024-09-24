package org.example.dao.Impl;

import org.example.dao.DevisDAO;
import org.example.models.Devis;
import org.example.models.Projet;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevisDAOImpl implements DevisDAO {
    private final Connection connection;

    public DevisDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addDevis(Devis devis) throws SQLException {
        String query = "INSERT INTO devis (montantEstime, dateEmission, dateValidite, accepte, project_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setObject(2, devis.getDateEmission());
            stmt.setObject(3, devis.getDateValidite());
            stmt.setBoolean(4, devis.isAccepte());
            stmt.setInt(5, devis.getProject().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Devis getDevis(int id) throws SQLException {
        String query = "SELECT * FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Projet projet = new Projet();
                LocalDate dateEmission = rs.getObject("dateEmission", LocalDate.class);
                LocalDate dateValidite = rs.getObject("dateValidite", LocalDate.class);
                return new Devis(
                        rs.getDouble("montantEstime"),
                        dateEmission,
                        dateValidite,
                        rs.getBoolean("accepte"),
                        projet

                );
            }
        }
        return null;
    }

    @Override
    public List<Devis> getAllDevis() throws SQLException {
        List<Devis> devisList = new ArrayList<>();
        String query = "SELECT * FROM devis";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Projet projet = new Projet();

                LocalDate dateEmission = rs.getObject("dateEmission", LocalDate.class);
                LocalDate dateValidite = rs.getObject("dateValidite", LocalDate.class);

                Devis devis = new Devis(
                        rs.getDouble("montantEstime"),
                        dateEmission,
                        dateValidite,
                        rs.getBoolean("accepte"),
                        projet
                );
                devisList.add(devis);
            }
        }
        return devisList;
    }


    @Override
    public void updateDevis(Devis devis) throws SQLException {
        String query = "UPDATE devis SET montantEstime = ?, dateEmission = ?, dateValidite = ?, accepte = ?, project_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setObject(2, devis.getDateEmission());
            stmt.setObject(3, devis.getDateValidite());
            stmt.setBoolean(4, devis.isAccepte());
            stmt.setInt(5, devis.getProject().getId());
            stmt.setInt(6, devis.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDevis(int id) throws SQLException {
        String query = "DELETE FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
