package org.example.UI;

import org.example.models.*;
import org.example.service.ClientService;
import org.example.service.MainDOeuvreService;
import org.example.service.MateriauService;
import org.example.service.ProjetService;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class PrincipalMenu {
    static Connection conn = DatabaseConnection.getConnection();



    public static void displayClient() throws SQLException {
        ClientService clientService = new ClientService(conn);
        boolean estProfessionel = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("\n---  Entrer votre nom ---\n");
        String nom = scan.nextLine();
        System.out.println("--- Entrer votre adresse ---\n");
        String adresse = scan.nextLine();
        System.out.println("--- Entrer votre telephone ---\n");
        String telephone = scan.nextLine();
        System.out.println("--- Etes-vous professionel (yes/no) ---\n");
        String p = scan.nextLine();

        if (p.equalsIgnoreCase("yes")) {
            estProfessionel = true;
        } else if (p.equalsIgnoreCase("no")) {
            estProfessionel = false;
        }

        Client client = new Client(nom, adresse, telephone, estProfessionel);
        clientService.createClient(client);

        int clientId = client.getId();

        if (clientId == 0) {
            System.out.println("Erreur: Impossible de récupérer l'ID du client.");
            return;
        }

        System.out.println("Client ajouté avec succès !");

        projectMenu(client);
    }



    public static void diplayMenu(Projet projet) throws SQLException {
        MateriauService materiauService = new MateriauService(conn);
        MainDOeuvreService mainDOeuvreService = new MainDOeuvreService(conn);
        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("--- Menu Principal ---");
            System.out.println("1. Ajouter des matériaux");
            System.out.println("2. Ajouter de la main-d'œuvre");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
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
                        scanner.nextLine();

                        Materiau materiau = new Materiau(nom, 12, coutUnitaire, quantite, coefficientQualite, projet);
                        materiauService.createMateriau(materiau);
                        System.out.println("Matériau ajouté avec succès !");

                        System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
                        addMoreMateriau = scanner.nextLine().equalsIgnoreCase("y");
                    }
                    break;

                case 2:
                    boolean addMoreMainDOeuvre = true;

                    while (addMoreMainDOeuvre) {
                        System.out.println("--- Ajout de la main-d'œuvre ---");
                        System.out.print("Entrez le nom de la main-d'œuvre : ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
                        double tauxHoraire = scanner.nextDouble();
                        System.out.print("Entrez le nombre d'heures travaillées : ");
                        double heuresTravail = scanner.nextDouble();
                        System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
                        double productiviteOuvrier = scanner.nextDouble();
                        scanner.nextLine();

                        // Create MainDOeuvre with the current Projet
                        MainDOeuvre mainDOeuvre = new MainDOeuvre(nom, 12,tauxHoraire,heuresTravail, productiviteOuvrier, projet);
                        mainDOeuvreService.createMainDOeuvre(mainDOeuvre);
                        System.out.println("Main-d'œuvre ajoutée avec succès !");

                        System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
                        addMoreMainDOeuvre = scanner.nextLine().equalsIgnoreCase("y");
                    }
                    break;

                case 3:
                    keepRunning = false;
                    System.out.println("Fermeture du programme...");
                    break;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();

    }

    public static void menuProjet() throws SQLException {
        Scanner scan = new Scanner(System.in);
        int choix;

        System.out.println("\n--- Menu ---\n");
        System.out.println("1- Creer un projet.");
        System.out.println("2- Afficher les projets existants.");
        System.out.println("3- Quitter.\n");

        System.out.println("--- Entrer votre choix");
        choix = scan.nextInt();

        switch (choix){
            case 1:
                clientMenu();
                break;

            case 2:
                System.out.println("\n--- calcluler le cout.");
                break;

            case 3:
                System.out.println("Fermeture du programme...");
                break;

            default:
                System.out.println("Option invalide, veuillez réessayer.");
        }
    }

    public static Projet projectMenu(Client client) throws SQLException {
        ProjetService projetService = new ProjetService(conn);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Menu ---\n");
        System.out.println("Entrer le nom du projet:");
        String nom = scanner.nextLine();
        System.out.println("Entrer la marge");
        double margeBenigiciaire = scanner.nextDouble();

        Projet projet = new Projet(nom, margeBenigiciaire, 0, EtatProjet.EN_COURS, client);
        projetService.addProjet(projet);

        diplayMenu(projet);

        return projet;
    }

    public static Client searchClient(int id){
        ClientService clientService = new ClientService(conn);
        Client client = clientService.getClient(id);
        if(client != null){
            return client;
        }else{
            return null;
        }
    }

    public static void clientMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Menu Client ---\n");
        System.out.println("1- Creer un nouveau client");
        System.out.println("2- Rechercher un client existant");
        System.out.println("3- Quitter.\n");
        System.out.println("--- Entrer votre choix");
        int choix = scanner.nextInt();

        switch (choix){
            case 1:
                displayClient();
                break;
            case 2:
                System.out.println("--- calcluler le cout.");
                break;
            case 3:
                System.out.println("Fermeture du programme...");
                break;

        }

    }
}


