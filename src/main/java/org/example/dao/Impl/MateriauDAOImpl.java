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
        // Check if the component already exists; if not, add it.
        if (!composantExists(materiau.getId())) {
            addComposant(new Composant(materiau.getNom(), "Materiau", 20.0));
        }

        // SQL query for inserting a material
        String query = "INSERT INTO Materiau (nom, typecomposant, tauxtva, coutUnitaire, quantite, coutTransport, coefficientQualite, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, materiau.getNom());
            stmt.setString(2, materiau.getTypeComposant());
            stmt.setDouble(3, materiau.getTauxTVA());
            stmt.setDouble(4, materiau.getCoutUnitaire());
            stmt.setDouble(5, materiau.getQuantite());
            stmt.setDouble(6, materiau.getCoutTransport());
            stmt.setDouble(7, materiau.getCoefficientQualite());
            stmt.setInt(8, materiau.getProjet().getId()); // Use setInt for project ID

            // Execute the update and retrieve the generated keys
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        materiau.setId(generatedId);
                        System.out.println("Generated Materiau ID in Java: " + materiau.getId());  // Debugging output
                        System.out.println("Materiau added successfully.");
                    } else {
                        System.out.println("Erreur: Aucun ID généré pour le matériau.");
                    }
                }
            } else {
                System.out.println("Erreur: Aucune ligne affectée par l'insertion.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        String insertQuery = "INSERT INTO Composant (id, nom, typeComposant, tauxTVA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, composant.getId());
            stmt.setString(2, composant.getNom());
            stmt.setString(3, composant.getTypeComposant());
            stmt.setDouble(4, composant.getTauxTVA());
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    composant.setId(generatedKeys.getInt(1));
                }
            }

            stmt.executeUpdate();
            System.out.println("Composant added successfully.");
        }
    }

}


