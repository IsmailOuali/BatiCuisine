package org.example;

import org.example.UI.PrincipalMenu;
import org.example.models.EtatProjet;
import org.example.models.Projet;
import org.example.service.ClientService;
import org.example.service.ProjetService;
import org.example.models.Client;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PrincipalMenu.menuProjet();
    }
}
