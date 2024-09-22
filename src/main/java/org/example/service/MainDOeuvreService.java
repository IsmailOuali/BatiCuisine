package org.example.service;

import org.example.models.MainDOeuvre;
import org.example.repository.MainDOeuvreRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class MainDOeuvreService {

    private final MainDOeuvreRepository mainDOeuvreRepository;

    public MainDOeuvreService(Connection connection) {
        this.mainDOeuvreRepository = new MainDOeuvreRepository(connection);
    }

    public void createMainDOeuvre(MainDOeuvre mainDOeuvre) throws SQLException {
        mainDOeuvreRepository.save(mainDOeuvre);
    }
}
