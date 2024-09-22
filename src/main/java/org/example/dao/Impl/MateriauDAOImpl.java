package org.example.dao.Impl;

import org.example.dao.MateriauDAO;
import org.example.models.Composant;
import org.example.models.Materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriauDAOImpl implements MateriauDAO {

    private Connection conn;

    public MateriauDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void addMateriau(Materiau materiau) throws SQLException {
        if (!composantExists(materiau.getId())) {
            addComposant(new Composant(materiau.getId(), materiau.getNom(),"Materiau", 20.0));
        }

        String query = "INSERT INTO Materiau (id, coutUnitaire, quantite, coutTransport, coefficientQualite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, materiau.getId());
            stmt.setDouble(2, materiau.getCoutUnitaire());
            stmt.setDouble(3, materiau.getQuantite());
            stmt.setDouble(4, materiau.getCoutTransport());
            stmt.setDouble(5, materiau.getCoefficientQualite());

            stmt.executeUpdate();
            System.out.println("Materiau added successfully.");
        } catch (SQLException e) {
            System.out.println("Error in adding Materiau");
            e.printStackTrace();
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

            stmt.executeUpdate();
            System.out.println("Composant added successfully.");
        }
    }

}


