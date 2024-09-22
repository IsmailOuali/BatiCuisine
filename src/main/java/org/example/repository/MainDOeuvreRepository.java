package org.example.repository;

import org.example.dao.Impl.MainDOeuvreDAOImpl;
import org.example.dao.MainDOeuvreDAO;
import org.example.models.MainDOeuvre;

import java.sql.Connection;
import java.sql.SQLException;

public class MainDOeuvreRepository {
    private MainDOeuvreDAO mainDOeuvreDAO;

    public MainDOeuvreRepository(Connection connection) {
        this.mainDOeuvreDAO = new MainDOeuvreDAOImpl(connection);
    }

    public void save(MainDOeuvre mainDOeuvre) throws SQLException {
        mainDOeuvreDAO.addMainDOeuvre(mainDOeuvre);
    }
}
