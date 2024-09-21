package org.example.UI;

import java.util.Scanner;

public class PrincipalMenu {
    static Scanner scan  = new Scanner(System.in);
    static int  choix;

    public static   String displayName()
    {

        System.out.println("\n---  Entrer votre nom ---\n");
        String nom = scan.nextLine();
        return nom;
}

        public static int displayPMenu()
    {
        System.out.println("\n---  Menu Principal ---\n");
        System.out.println(" 1- Creer un nouveau  projet");
        System.out.println(" 2- Afficher les projets existans");
        System.out.println(" 3- Calculer le cout d'un projet existant");
        System.out.println(" 4- Quitter\n");
        System.out.println("--- Entrez votre choix");
         choix = scan.nextInt();
         return choix;
    }

}
