package org.example.repository;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.MainDOeuvre;

public class MainDOeuvreRepository {
    private MainDOeuvreDAO mainDOeuvreDAO;

    public MainDOeuvreRepository(MainDOeuvreDAO mainDOeuvreDAO) {
        this.mainDOeuvreDAO = mainDOeuvreDAO;
    }

    public void save(MainDOeuvre mainDOeuvre) {
        mainDOeuvreDAO.addMainDOeuvre(mainDOeuvre);
    }
}
