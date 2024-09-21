package org.example.dao.Impl;

import org.example.dao.MateriauDAO;
import org.example.models.Materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MateriauDAOImpl implements MateriauDAO {

    private Connection conn;

    public MateriauDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void addMateriau(Materiau materiau)
    {
        String query = "INSERT INTO Materiau (id, coutUnitaire, quantite, coutTransport, coefficientQualite) VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, materiau.getId());
            stmt.setDouble(2, materiau.getCoutUnitaire());
            stmt.setDouble(3, materiau.getQuantite());
            stmt.setDouble(4, materiau.getCoefficientQualite());

            stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Error in adding Materiau");
            e.printStackTrace();
        }

    }

}
