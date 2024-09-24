package org.example.repository.Interface;

import org.example.models.Devis;

import java.sql.SQLException;
import java.util.List;

public interface DevisRepositoryInterface {
    void save(Devis devis) throws SQLException;
    void deleteById(int id) throws SQLException;
    void update(Devis devis) throws SQLException;
    List<Devis> findAll() throws SQLException;
    Devis findById(int id) throws SQLException;
}
