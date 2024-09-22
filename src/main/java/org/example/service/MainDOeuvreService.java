package org.example.service;

import org.example.models.MainDOeuvre;
import org.example.repository.MainDOeuvreRepository;

import java.sql.SQLException;

public class MainDOeuvreService {

    private final MainDOeuvreRepository mainDOeuvreRepository;

    public MainDOeuvreService(MainDOeuvreRepository mainDOeuvreRepository) {
        this.mainDOeuvreRepository = mainDOeuvreRepository;
    }

    public void createMainDOeuvre(MainDOeuvre mainDOeuvre) throws SQLException {
        mainDOeuvreRepository.save(mainDOeuvre);
    }
}
