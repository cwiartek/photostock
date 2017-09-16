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

    public void show() {

        Menu menu = new Menu(scanner);
        menu.setTitleLabel("WITAMY W PHOTOSTOCK");
        menu.addItem("Wyszukaj produkty", () -> searchScreen.show());
        menu.addItem("Light Boxy", () -> lightBoxManagmentsScreen.show());
        menu.setLastItemLabel("Zakoncz");
        menu.show();
    }


}
