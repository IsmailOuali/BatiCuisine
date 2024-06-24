package org.example;

import org.example.UI.PrincipalMenu;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();

        // Initialize the PrincipalMenu with the database connection
        PrincipalMenu menu = new PrincipalMenu(conn);

        try {
            // Start the client display menu
            menu.clientExist();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // It's a good practice to close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
