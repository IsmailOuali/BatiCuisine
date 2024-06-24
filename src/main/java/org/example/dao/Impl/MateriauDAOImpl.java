package org.example.dao.Impl;

import org.example.dao.MateriauDAO;
import org.example.models.Composant;
import org.example.models.Materiau;
import org.example.models.Projet;

import java.sql.*;

public class MateriauDAOImpl implements MateriauDAO {

    private Connection conn;

    public MateriauDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void addMateriau(Materiau materiau) throws SQLException {
        if (!composantExistsByName(materiau.getNom())) {
            Composant composant = new Composant(materiau.getNom(), "Materiau", materiau.getTauxTVA());
            addComposant(composant);
            materiau.setId(composant.getId());
        }

        // Check that the project is valid
        if (materiau.getProjet() == null || materiau.getProjet().getId() <= 0) {
            System.out.println("Erreur: Projet associé invalide.");
            return;
        }

        // Insert Materiau using the generated composant ID
        String query = "INSERT INTO Materiau (nom, typecomposant, tauxTVA, coutUnitaire, quantite, coutTransport, coefficientQualite, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, materiau.getNom());
            stmt.setString(2, materiau.getTypeComposant());
            stmt.setDouble(3, materiau.getTauxTVA());
            stmt.setDouble(4, materiau.getCoutUnitaire());
            stmt.setDouble(5, materiau.getQuantite());
            stmt.setDouble(6, materiau.getCoutTransport());
            stmt.setDouble(7, materiau.getCoefficientQualite());
            stmt.setInt(8, materiau.getProjet().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        materiau.setId(generatedId);
                        System.out.println("Generated Materiau ID in Java: " + materiau.getId());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du matériau : " + e.getMessage());
            throw e;
        }
    }



    private boolean composantExists(int id) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM Composant WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private void addComposant(Composant composant) throws SQLException {
        // No need to insert the 'id' field, as it's auto-incremented
        String insertQuery = "INSERT INTO Composant (nom, typeComposant, tauxTVA) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, composant.getNom());
            stmt.setString(2, composant.getTypeComposant());
            stmt.setDouble(3, composant.getTauxTVA());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated key
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        composant.setId(generatedId); // Set the auto-generated ID to the object
                        System.out.println("Generated Composant ID in Java: " + composant.getId());
                    }
                }
            }
            System.out.println("Composant added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding Composant: " + e.getMessage());
            throw e;
        }
    }


    public boolean composantExistsByName(String nom) throws SQLException {
        String query = "SELECT COUNT(*) FROM Composant WHERE nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}


