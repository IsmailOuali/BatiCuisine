package org.example;

import org.example.models.MainDOeuvre;
import org.example.models.Materiau;
import org.example.service.MainDOeuvreService;
import org.example.service.MateriauService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import org.example.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        MateriauService materiauService = new MateriauService(conn);
        MainDOeuvreService mainDOeuvreService = new MainDOeuvreService(conn);
        Scanner scanner = new Scanner(System.in);

        // Menu loop
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("--- Menu Principal ---");
            System.out.println("1. Ajouter des matériaux");
            System.out.println("2. Ajouter de la main-d'œuvre");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add materials using MateriauService
                    boolean addMoreMateriau = true;
                    while (addMoreMateriau) {
                        System.out.println("--- Ajout des matériaux ---");
                        System.out.print("Entrez le nom du matériau : ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez la quantité de ce matériau : ");
                        double quantite = scanner.nextDouble();
                        System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
                        double coutUnitaire = scanner.nextDouble();
                        System.out.print("Entrez le coût de transport de ce matériau (€) : ");
                        double coutTransport = scanner.nextDouble();
                        System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
                        double coefficientQualite = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        Materiau materiau = new Materiau( nom, 20, coutUnitaire, quantite, coefficientQualite);
                        materiauService.createMateriau(materiau);  // Using the service instead of DAO
                        System.out.println("Matériau ajouté avec succès !");

                        System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
                        addMoreMateriau = scanner.nextLine().equalsIgnoreCase("y");
                    }
                    break;

                case 2:
                    // Add labor using MainDOeuvreService
                    boolean addMoreMainDOeuvre = true;
                    while (addMoreMainDOeuvre) {
                        System.out.println("--- Ajout de la main-d'œuvre ---");
                        System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
                        String type = scanner.nextLine();
                        System.out.print("Entrez le nom de main-d'œuvre: ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
                        double tauxHoraire = scanner.nextDouble();
                        System.out.print("Entrez le nombre d'heures travaillées : ");
                        double heuresTravail = scanner.nextDouble();
                        System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
                        double productiviteOuvrier = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        MainDOeuvre mainDOeuvre = new MainDOeuvre( nom, 21, tauxHoraire, heuresTravail, productiviteOuvrier);
                        mainDOeuvreService.createMainDOeuvre(mainDOeuvre);  // Using the service instead of DAO
                        System.out.println("Main-d'œuvre ajoutée avec succès !");

                        System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
                        addMoreMainDOeuvre = scanner.nextLine().equalsIgnoreCase("y");
                    }
                    break;

                case 3:
                    // Exit the program
                    keepRunning = false;
                    System.out.println("Fermeture du programme...");
                    break;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }

        scanner.close();
    }
}
