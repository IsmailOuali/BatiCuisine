package org.example.dao.Impl;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.Composant;
import org.example.models.MainDOeuvre;

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
            addComposant(new Composant(mainDOeuvre.getId(), mainDOeuvre.getNom(), "MainDOeuvre", 20.0));
        }

        String query = "INSERT INTO MainDOeuvre ( id, tauxHoraire, heuresTravail, productiviteOuvrier) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mainDOeuvre.getId());
            stmt.setDouble(2, mainDOeuvre.getTauxHoraire());
            stmt.setDouble(3, mainDOeuvre.getHeursTravail());
            stmt.setDouble(4, mainDOeuvre.getProductiviteOuvrier());

            stmt.executeUpdate();
            System.out.println("MainDOeuvre added successfully.");
        } catch (SQLException e) {
            System.out.println("Error in adding MainDOeuvre");
            e.printStackTrace();
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
