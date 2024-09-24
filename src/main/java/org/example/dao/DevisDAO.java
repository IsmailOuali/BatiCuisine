package org.example.dao;

import org.example.models.Devis;

import java.sql.SQLException;
import java.util.List;

public interface DevisDAO {
    void addDevis(Devis devis) throws SQLException;
    Devis getDevis(int id) throws SQLException;
    List<Devis> getAllDevis() throws SQLException;
    void updateDevis(Devis devis) throws SQLException;
    void deleteDevis(int id) throws SQLException;
}
