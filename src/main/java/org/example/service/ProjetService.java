package org.example.service;

import org.example.models.Projet;
import org.example.repository.ProjetRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjetService {
    private  ProjetRepository projetRepository;

    public ProjetService(Connection connection) {
        this.projetRepository = new ProjetRepository(connection);
    }

    public void addProjet(Projet projet) throws SQLException {
        projetRepository.save(projet);
    }

    public Projet getProjetById(int id) throws SQLException {
        return projetRepository.getProjetById(id);
    }

    public List<Projet> getAllProjets() throws SQLException {
        return projetRepository.getAllProjets();
    }

    public void updateProjet(Projet projet) throws SQLException {
        projetRepository.updateProjet(projet);
    }

    public void deleteProjet(int id) throws SQLException {
        projetRepository.deleteProjet(id);
    }
}
