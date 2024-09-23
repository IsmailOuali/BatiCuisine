package org.example.repository.Interface;

import org.example.models.Projet;

import java.sql.Connection;
import java.util.List;

public interface ProjetRepositoryInterface {

    public void  ProjetRepository(Connection connection);
    void save(Projet projet);
    Projet getProjetById(int id);
    List<Projet> getAllProjets();
    void updateProjet(Projet projet);
    void deleteProjet(int id);


}
