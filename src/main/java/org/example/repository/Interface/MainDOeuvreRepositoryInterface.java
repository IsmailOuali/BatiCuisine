package org.example.repository.Interface;

import org.example.dao.MainDOeuvreDAO;
import org.example.models.MainDOeuvre;

public interface MainDOeuvreRepositoryInterface {

    public void MainDOeuvreRepository(MainDOeuvreDAO mainDOeuvreDAO);
    void save(MainDOeuvre mainDOeuvre);
}
