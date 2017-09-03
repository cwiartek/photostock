package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

public class AuthenticationScreen {

    private AuthenticationManager authenticationManager;
    private Scanner scanner;

    public AuthenticationScreen(Scanner scanner,AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("Podaj login");
            String login = scanner.nextLine();
            if (authenticationManager.authentication(login))
                return;
            else
                System.out.println("Nieprawidlowy login");
        }
    }
}
