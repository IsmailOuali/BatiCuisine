package org.example.dao;

import org.example.models.Projet;

import java.sql.SQLException;
import java.util.List;

public interface ProjetDAO {
    void addProjet(Projet projet) throws SQLException;
    Projet getProjetById(int id) throws SQLException;
    List<Projet> getAllProjets() throws SQLException;
    void updateProjet(Projet projet) throws SQLException;
    void deleteProjet(int id) throws SQLException;
}
