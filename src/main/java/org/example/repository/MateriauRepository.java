package org.example.repository;

import org.example.dao.MateriauDAO;
import org.example.models.Materiau;

import java.sql.SQLException;

public class MateriauRepository {

    private MateriauDAO materiauDAO;

    public MateriauRepository(MateriauDAO materiauDAO) {
        this.materiauDAO = materiauDAO;
    }

    public void save(Materiau materiau) throws SQLException {
        materiauDAO.addMateriau(materiau);
    }
}
