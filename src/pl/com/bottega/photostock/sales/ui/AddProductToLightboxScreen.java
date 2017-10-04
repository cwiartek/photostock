package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightboxManagment;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.Scanner;

public class AddProductToLightboxScreen {

    private LightboxManagment lightboxManagment;
    private Scanner scanner;

    public AddProductToLightboxScreen (LightboxManagment lightboxManagment, Scanner scanner) {
        this.lightboxManagment = lightboxManagment;
        this.scanner = scanner;
    }

    public void show(LightBox lightBox) {
        System.out.println("Podaj numer obrazka, który chcesz dodać: ");
        Long pictureNumber = scanner.nextLong();
        scanner.nextLine();
        lightboxManagment.add(lightBox.getNumber(), pictureNumber);
        System.out.println(String.format("Obrazek o numerze %d został dodany.", pictureNumber));
    }
}
