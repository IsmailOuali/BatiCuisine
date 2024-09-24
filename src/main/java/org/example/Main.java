package org.example;

import org.example.UI.PrincipalMenu;
import org.example.service.ClientService;
import org.example.models.Client;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        PrincipalMenu.displayClient();
    }

}
