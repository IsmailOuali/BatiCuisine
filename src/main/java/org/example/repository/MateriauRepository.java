package org.example.repository;

import org.example.dao.Impl.MateriauDAOImpl;
import org.example.dao.MateriauDAO;
import org.example.models.Materiau;

import java.sql.Connection;
import java.sql.SQLException;

public class MateriauRepository {

    private MateriauDAO materiauDAO;

    public MateriauRepository(Connection connection) {
        this.materiauDAO = new MateriauDAOImpl(connection);
    }

    public void save(Materiau materiau) throws SQLException {
        materiauDAO.addMateriau(materiau);
    }
}
