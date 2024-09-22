package org.example;


import org.example.UI.PrincipalMenu;

public class Main {
    public static void main(String[] args) {

        String name = PrincipalMenu.displayName();
        System.out.println("Hello " + name);
        PrincipalMenu.displayPMenu();
    }
}