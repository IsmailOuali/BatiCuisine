package org.example.repository;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.MainDOeuvre;

import java.sql.SQLException;

public class MainDOeuvreRepository {
    private MainDOeuvreDAO mainDOeuvreDAO;

    public MainDOeuvreRepository(MainDOeuvreDAO mainDOeuvreDAO) {
        this.mainDOeuvreDAO = mainDOeuvreDAO;
    }

    public void save(MainDOeuvre mainDOeuvre) throws SQLException {
        mainDOeuvreDAO.addMainDOeuvre(mainDOeuvre);
    }
}
