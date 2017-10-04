package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightboxManagment;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LightBoxManagmentsScreen {

    private Scanner scanner;
    private LightboxManagment lightBoxManagement;
    private AuthenticationManager authenticationManager;
    private List<LightBox> lightBoxes;
    private LightBox lightBox;
    private AddProductToLightboxScreen addProductToLightBoxScreen;
    private PurchaseLightBoxScreen purchaseLightBoxScreen;

    public LightBoxManagmentsScreen(Scanner scanner, LightboxManagment lightBoxManagement,

                                    AuthenticationManager authenticationManager, AddProductToLightboxScreen addProductToLightBoxScreen,
                                    PurchaseLightBoxScreen purchaseLightBoxScreen) {

        this.scanner = scanner;
        this.lightBoxManagement = lightBoxManagement;
        this.authenticationManager = authenticationManager;
        this.addProductToLightBoxScreen = addProductToLightBoxScreen;
        this.purchaseLightBoxScreen = purchaseLightBoxScreen;
    }

    public void show() {

        System.out.println("Twoje lajt boksy:");
        lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());
        if (lightBoxes.isEmpty())
            System.out.println("Nie masz aktualnie żadnych lajt boksów");
        else {
            int index = 1;
            for (LightBox lightBox : lightBoxes)
                System.out.println(String.format("%d. %s", index++, lightBox.getName()));
        }
        lightBoxActions();
    }

    private void lightBoxActions() {

        Menu menu = new Menu(scanner);
        menu.addItem("Dodaj nowy Light Box", () -> addNewLightBox());
        if (lightBoxes.size() >0) { menu.addItem("Wyświetl LightBox", () -> showLightBox());}
        menu.setLastItemLabel("Wroc do poprzedniego menu");
        menu.show();
    }

    private void showLightBox() {
        System.out.println("Podaj index Lightbox'a: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        lightBox = lightBoxes.get(index - 1);
        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(lightBox);
        selectedLightBoxActions();
    }

    private void selectedLightBoxActions() {

        Menu menu = new Menu(scanner);
        menu.addItem("Dodaj produkt do Light Boxa", () -> addProductToLightBoxScreen.show(lightBox));
        menu.addItem("Zakup produkty z Light Boxa", () -> {
            try {
                 purchaseLightBoxScreen.show(lightBox);
            }
            catch (IOException e) {
                System.out.println("Nie znaleziono pliku");
                }
                });
        menu.setLastItemLabel("Wroc do poprzedniego menu");
        menu.show();
    }

    private void addNewLightBox() {
        System.out.println("Podaj nazwę nowego LighBox'a: ");
        String name = scanner.nextLine();
        String clientNumber = authenticationManager.getClientNumber();
        lightBoxManagement.createLightbox(clientNumber, name);
        lightBoxes = lightBoxManagement.getLightBoxes(clientNumber);
        System.out.println(String.format("LightBox %s został dodany.", name));
    }


}
