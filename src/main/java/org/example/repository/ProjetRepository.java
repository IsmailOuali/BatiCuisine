package org.example.repository;

import org.example.dao.Impl.ProjetDAOImpl;
import org.example.dao.ProjetDAO;
import org.example.models.Projet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjetRepository {
    private final ProjetDAO projetDAO;

    public ProjetRepository(Connection connection) {
        this.projetDAO = new ProjetDAOImpl(connection);
    }

    public void save(Projet projet) throws SQLException {
        projetDAO.addProjet(projet);
    }

    public Projet getProjetById(int id) throws SQLException {
        return projetDAO.getProjetById(id);
    }

    public List<Projet> getAllProjets() throws SQLException {
        return projetDAO.getAllProjets();
    }

    public void updateProjet(Projet projet) throws SQLException {
        projetDAO.updateProjet(projet);
    }

    public void deleteProjet(int id) throws SQLException {
        projetDAO.deleteProjet(id);
    }
}
