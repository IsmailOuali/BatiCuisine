package org.example.dao.Impl;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.Composant;
import org.example.models.MainDOeuvre;
import org.example.models.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDOeuvreDAOImpl implements MainDOeuvreDAO {

    private Connection conn;

    public MainDOeuvreDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void addMainDOeuvre(MainDOeuvre mainDOeuvre) throws SQLException {
        if (!composantExists(mainDOeuvre.getId())) {
            Projet projet = new Projet();
            addComposant(new Composant(mainDOeuvre.getNom(), "MainDOeuvre", 20.0));

        }

        String query = "INSERT INTO MainDOeuvre (  nom, typeComposant, tauxTVA,tauxHoraire, heuresTravail, productiviteOuvrier, project_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mainDOeuvre.getNom());
            stmt.setString(2, mainDOeuvre.getTypeComposant());
            stmt.setDouble(3, mainDOeuvre.getTauxTVA());
            stmt.setDouble(4, mainDOeuvre.getTauxHoraire());
            stmt.setDouble(5, mainDOeuvre.getHeursTravail());
            stmt.setDouble(6, mainDOeuvre.getProductiviteOuvrier());
            stmt.setInt(7, mainDOeuvre.getProjet().getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                mainDOeuvre.setId(generatedId);
                System.out.println("Generated client ID in Java: " + mainDOeuvre.getId());  // Debugging output
            } else {
                System.out.println("Erreur: Aucun ID généré pour le client.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    private void addComposant(Composant composant) throws SQLException {
        String insertQuery = "INSERT INTO Composant (id, nom, typeComposant, tauxTVA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, composant.getId());
            stmt.setString(2, composant.getNom());
            stmt.setString(3, composant.getTypeComposant());
            stmt.setDouble(4, composant.getTauxTVA());

            stmt.executeUpdate();
            System.out.println("Composant added successfully.");
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

}
