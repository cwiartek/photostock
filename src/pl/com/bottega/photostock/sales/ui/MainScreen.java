package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

public class MainScreen {

    private LightBoxManagmentsScreen lightBoxManagmentsScreen;
    private SearchScreen searchScreen;
    private Scanner scanner;

    public MainScreen(Scanner scanner,LightBoxManagmentsScreen lightBoxManagmentsScreen, SearchScreen searchScreen) {
        this.scanner = scanner;
        this.lightBoxManagmentsScreen = lightBoxManagmentsScreen;
        this.searchScreen = searchScreen;
    }

    public void show(){
        while(true) {
            showMenu();
            int decission = scanner.nextInt();
            scanner.nextLine();
            switch (decission) {
                case 1:
                    searchScreen.show();
                    break;
                case 2:
                    lightBoxManagmentsScreen.show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Sorry ale nie rozumiem!!");

            }

        }



    }
    private void showMenu() {
        System.out.println("!! Witamy w PHOTOSTOCK");
        System.out.println("1. Wyszukaj produkty");
        System.out.println("2. Light boxy");
        System.out.println("3. Zakoncz");
        System.out.print("Co chcesz zrobic?");
    }
}
