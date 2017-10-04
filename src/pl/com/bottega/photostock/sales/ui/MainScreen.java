package pl.com.bottega.photostock.sales.ui;

import java.io.IOException;
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

    public void show() {

        Menu menu = new Menu(scanner);
        menu.setTitleLabel("WITAMY W PHOTOSTOCK");
        menu.addItem("Wyszukaj produkty", () -> {
                    try {
                        searchScreen.show();
                    } catch (IOException e) {
                        System.out.println("Nie znaleziono pliku");
                    }
                });
        menu.addItem("Light Boxy", () -> lightBoxManagmentsScreen.show());
        menu.setLastItemLabel("Zakoncz");
        menu.show();
    }


}
