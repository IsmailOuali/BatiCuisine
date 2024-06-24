package org.example.UI;

import org.example.Main;
import org.example.models.*;
import org.example.service.*;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalMenu {
    static Connection conn = DatabaseConnection.getConnection();

    static ProjectCalculateService calculationService = new ProjectCalculateService();
    private static  MainDOeuvreService mainDOeuvreService;
    private static MateriauService materiauService;

    public PrincipalMenu(Connection conn) {
        this.mainDOeuvreService = new MainDOeuvreService(conn);
    }

    public static void clientExist() throws SQLException {
        ClientService clientService = new ClientService(conn);
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu Client ---");
            System.out.println("1. Créer un nouveau client");
            System.out.println("2. Chercher un client existant");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayClient();
                    break;

                case 2:
                    System.out.print("Entrez le nom du client : ");
                    String nomClient = scan.nextLine();

                    Client client = clientService.getClient(nomClient);
                    if (client != null) {
                        System.out.println("Client trouvé avec succès !");
                        Projet projet = displayProjet(client);
                        diplayMenu(projet);
                    } else {
                        System.out.println("Client introuvable. Retour au menu principal.");
                    }
                    break;

                case 3:
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    public static Projet displayProjet(Client client) throws SQLException {
        ProjetService projetService = new ProjetService(conn);
        Scanner scan = new Scanner(System.in);

        System.out.println("--- Entrez le nom du projet ---\n");
        String nomProjet = scan.nextLine();

        System.out.println("--- Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) ---\n");
        double margeBeneficiaire = 0;
        boolean applyMarge = scan.nextLine().equalsIgnoreCase("y");
        if (applyMarge) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            margeBeneficiaire = scan.nextDouble();
            scan.nextLine();
        }

        Projet projet = new Projet(nomProjet, margeBeneficiaire, 0, EtatProjet.EN_COURS, client);
        projetService.addProjet(projet);

        System.out.println("Projet ajouté avec succès avec l'ID : " + projet.getId());

        return projet;
    }


    public static void displayClient() throws SQLException {
        ClientService clientService = new ClientService(conn);
        boolean estProfessionel = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- Entrer votre nom ---\n");
        String nom = scan.nextLine();
        System.out.println("--- Entrer votre adresse ---\n");
        String adresse = scan.nextLine();
        System.out.println("--- Entrer votre téléphone ---\n");
        String telephone = scan.nextLine();
        System.out.println("--- Êtes-vous professionnel (yes/no) ---\n");
        String p = scan.nextLine();

        if (p.equalsIgnoreCase("yes")) {
            estProfessionel = true;
        } else if (p.equalsIgnoreCase("no")) {
            estProfessionel = false;
        }

        // Create the client
        Client client = new Client(nom, adresse, telephone, estProfessionel);
        clientService.createClient(client);

        int clientId = client.getId();
        if (clientId == 0) {
            System.out.println("Erreur : Impossible de récupérer l'ID du client.");
            return;
        }

        System.out.println("Client ajouté avec succès !");

        Projet projet = displayProjet(client);

        diplayMenu(projet);
    }


    public static void diplayMenu(Projet projet) throws SQLException {
        MateriauService materiauService = new MateriauService(conn);
        List<Materiau> materiaux = new ArrayList<>();
        List<MainDOeuvre> labor = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("--- Menu Principal ---");
            System.out.println("1. Ajouter des matériaux");
            System.out.println("2. Ajouter de la main-d'œuvre");
            System.out.println("3. Calculer le coût total et quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Materiau materiau = createMaterial(projet, scanner);
                    materiauService.createMateriau(materiau);
                    materiaux.add(materiau);
                    System.out.println("Matériau ajouté avec succès !");
                    break;

                case 2:
                    MainDOeuvre mainDOeuvre = createLabor(projet, scanner);
                    mainDOeuvreService.createMainDOeuvre(mainDOeuvre);
                    labor.add(mainDOeuvre);
                    System.out.println("Main-d'œuvre ajoutée avec succès !");
                    break;

                case 3:
                    calculateAndDisplayCosts(materiaux, labor, projet, scanner);
                    break;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static Materiau createMaterial(Projet projet, Scanner scanner) {

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

        Materiau materiau = new Materiau(nom, 12, coutUnitaire, quantite, coefficientQualite, projet);

        return materiau;
    }

    private static MainDOeuvre createLabor(Projet projet, Scanner scanner) {
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
        MainDOeuvre mainDOeuvre = new MainDOeuvre(nom, 12, tauxHoraire, heuresTravail, productiviteOuvrier, projet);
        mainDOeuvre.setProjetId(projet.getId());

        try {
            mainDOeuvreService.createMainDOeuvre(mainDOeuvre);
            System.out.println("Main-d'œuvre ajoutée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de la main-d'œuvre : " + e.getMessage());
            return null;
        }

        return mainDOeuvre;

    }

    private static void calculateAndDisplayCosts(List<Materiau> materiaux, List<MainDOeuvre> labor, Projet projet, Scanner scanner) throws SQLException {
        double tva = 1;
        double margeBeneficiaire = projet.getMargeBeneficiaire();

        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        boolean applyTva = scanner.nextLine().equalsIgnoreCase("y");
        if (applyTva) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            tva = scanner.nextDouble();
            scanner.nextLine();
        }

        double totalMaterialCost = calculationService.calculateTotalMaterialCost(materiaux, tva);
        double totalLaborCost = calculationService.calculateTotalLaborCost(labor, tva);
        double finalCost = calculationService.calculateFinalProjectCost(totalMaterialCost, totalLaborCost, margeBeneficiaire);

        System.out.println("--- Résultat du Calcul ---");
        System.out.printf("Coût total des matériaux avec TVA : %.2f €\n", totalMaterialCost);
        System.out.printf("Coût total de la main-d'œuvre avec TVA : %.2f €\n", totalLaborCost);
        System.out.printf("Coût total final du projet avec marge bénéficiaire : %.2f €\n", finalCost);

        double newCoutTotal = projet.getCoutTotal() + finalCost;
        projet.setCoutTotal(newCoutTotal);

        enregistrerDevis(newCoutTotal, projet);

        System.out.printf("Nouveau coût total du projet : %.2f €\n", projet.getCoutTotal());

    }

    public static void enregistrerDevis(double montantEstime, Projet projet) throws SQLException {
        DevisService devisService = new DevisService(conn);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEmission = null;
        LocalDate dateValidite = null;

        System.out.println("--- Enregistrement du Devis ---");

        // Get and validate DateEmission
        while (true) {
            try {
                System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
                String dateEmissionStr = scanner.nextLine();
                dateEmission = LocalDate.parse(dateEmissionStr, formatter);

                // Check if dateEmission is not in the future
                if (dateEmission.isAfter(LocalDate.now())) {
                    System.out.println("Erreur: La date d'émission ne peut pas être dans le futur.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur: Format de date invalide. Veuillez réessayer.");
            }
        }

        while (true) {
            try {
                System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
                String dateValiditeStr = scanner.nextLine();
                dateValidite = LocalDate.parse(dateValiditeStr, formatter);


                if (dateValidite.isBefore(dateEmission)) {
                    System.out.println("Erreur: La date de validité doit être après la date d'émission.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur: Format de date invalide. Veuillez réessayer.");
            }
        }


        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            Devis devis = new Devis(montantEstime, dateEmission, dateValidite, true, projet);
            devisService.createDevis(devis);
            System.out.println(devis.toString());
        } else {
            System.out.println("Le devis n'a pas été enregistré.");
        }
    }


}
