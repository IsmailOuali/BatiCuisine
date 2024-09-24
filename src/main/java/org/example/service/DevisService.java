package org.example.service;

import org.example.models.Devis;
import org.example.repository.DevisRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DevisService {
    private final DevisRepository devisRepository;

    public DevisService(Connection connection) {
        this.devisRepository = new DevisRepository(connection);
    }

    public void createDevis(Devis devis) throws SQLException {
        devisRepository.save(devis);
    }

    public Devis getDevis(int id) throws SQLException {
        return devisRepository.findById(id);
    }

    public List<Devis> getAllDevis() throws SQLException {
        return devisRepository.findAll();
    }

    public void updateDevis(Devis devis) throws SQLException {
        devisRepository.update(devis);
    }

    public void deleteDevis(int id) throws SQLException {
        devisRepository.deleteById(id);
    }
}
