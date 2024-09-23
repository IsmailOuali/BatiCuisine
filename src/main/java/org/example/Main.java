package org.example;

import org.example.models.EtatProjet;
import org.example.models.Projet;
import org.example.service.ClientService;
import org.example.service.ProjetService;
import org.example.models.Client;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();

        ProjetService projetService = new ProjetService(conn);

        // Create a new project
        Projet projet = new Projet("Renovation Kitchen", 0.20, 10000, EtatProjet.ANNULE, 2);

        // Add project to the database
        try {
            projetService.addProjet(projet);
            System.out.println("Project added successfully!");
        } catch (Exception e) {
            System.out.println("Error while adding project.");
            e.printStackTrace();
        }
    }
}
