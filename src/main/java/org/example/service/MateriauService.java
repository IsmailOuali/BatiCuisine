package org.example.service;


import org.example.models.Materiau;
import org.example.repository.MateriauRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class MateriauService {
    private final MateriauRepository materiauRepository;

    public MateriauService(Connection connection) {
        this.materiauRepository = new MateriauRepository(connection);
    }

    public void createMateriau(Materiau materiau ) throws SQLException
    {
        materiauRepository.save(materiau);
    }
}
