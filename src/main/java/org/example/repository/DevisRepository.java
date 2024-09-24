package org.example.repository;

import org.example.dao.DevisDAO;
import org.example.dao.Impl.DevisDAOImpl;
import org.example.models.Devis;
import org.example.repository.Interface.DevisRepositoryInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DevisRepository implements DevisRepositoryInterface {
    private final DevisDAO devisDAO;

    public DevisRepository(Connection connection) {
        this.devisDAO = new DevisDAOImpl(connection);
    }

    @Override
    public void save(Devis devis) throws SQLException {
        devisDAO.addDevis(devis);
    }

    @Override
    public Devis findById(int id) throws SQLException {
        return devisDAO.getDevis(id);
    }

    @Override
    public List<Devis> findAll() throws SQLException {
        return devisDAO.getAllDevis();
    }

    @Override
    public void update(Devis devis) throws SQLException {
        devisDAO.updateDevis(devis);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        devisDAO.deleteDevis(id);
    }
}
