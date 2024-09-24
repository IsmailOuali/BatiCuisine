package org.example;

import org.example.UI.PrincipalMenu;
import org.example.models.Devis;
import org.example.models.Projet;
import org.example.service.ClientService;
import org.example.models.Client;
import org.example.service.DevisService;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Assuming you have a method to get a database connection
        Connection connection = DatabaseConnection.getConnection();
        DevisService devisService = new DevisService(connection);

        // Create a new Projet (this should already exist in your database)
        Projet projet = new Projet();
        projet.setId(10); // Set the existing project ID

        // Create a new Devis
        Devis newDevis = new Devis(1000.0, LocalDate.now(), LocalDate.now().plusDays(30), false, projet);

        // Save the Devis
        devisService.createDevis(newDevis);
        System.out.println("Devis created and saved.");

        // Retrieve the Devis by ID (assuming the ID is generated and known)
         // Adjust the ID as needed

        // Print the Devis
        System.out.println("Retrieved Devis: " + newDevis.toString());
    }

}
