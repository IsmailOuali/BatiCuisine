package org.example.dao.Impl;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.MainDOeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainDOeuvreDAOImpl implements MainDOeuvreDAO {

    private Connection conn;

    public MainDOeuvreDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void addMainDOeuvre(MainDOeuvre mainDOeuvre) {

        String query = "INSERT INTO MainDOeuvre (id, tauxHoraire, heuresTravail, productiviteOuvirier) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mainDOeuvre.getId());
            stmt.setDouble(2, mainDOeuvre.getTauxHoraire());
            stmt.setDouble(3, mainDOeuvre.getProductiviteOuvrier());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
