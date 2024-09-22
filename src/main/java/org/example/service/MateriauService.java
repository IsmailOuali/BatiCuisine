package org.example.service;


import org.example.models.Materiau;
import org.example.repository.MateriauRepository;

import java.sql.SQLException;

public class MateriauService {
    private final MateriauRepository materiauRepository;

    public MateriauService(MateriauRepository materiauRepository) {
        this.materiauRepository = materiauRepository;
    }

    public void createMateriau(Materiau materiau ) throws SQLException
    {
        materiauRepository.save(materiau);
    }
}
