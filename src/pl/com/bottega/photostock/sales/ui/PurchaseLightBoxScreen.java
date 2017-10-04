package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightboxManagment;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.PurchaseStatus;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PurchaseLightBoxScreen {

    private LightboxManagment lightboxManagment;
    private PurchaseProcess purchaseProcess;
    private Scanner scanner;

    public PurchaseLightBoxScreen(LightboxManagment lightboxManagment, PurchaseProcess purchaseProcess, Scanner scanner) {
        this.lightboxManagment = lightboxManagment;
        this.purchaseProcess = purchaseProcess;
        this.scanner = scanner;
    }

    public void show(LightBox lightBox) throws IOException {
        Set<Long> numbers = new HashSet<>();
        for ( Product p : lightBox.getItems())
            numbers.add(p.getNumber());

        String reservationNumber = purchaseProcess.createReservation(lightBox.getOwner().getNumber());

        lightboxManagment.reserve(lightBox.getNumber(),numbers, reservationNumber);
        Offer offer = purchaseProcess.calculateOffer(reservationNumber);
        System.out.println(String.format("Cena wybranych zdjec: %s", offer.getTotalCost()));
        System.out.print("Czy chesz dokonac zakupu ( t/n");
        String decision = scanner.nextLine();
        if ( decision.equals("t")) {
           PurchaseStatus status = purchaseProcess.confirm(reservationNumber, offer);
           if( status.equals(PurchaseStatus.SUCCESS))
               System.out.println("Dziekujemy za udane zakupy");
           else if (status.equals(PurchaseStatus.NO_ENOUGH_FOUNDS))
               System.out.println("Przykro mi nie masz wystarczajacych srodkow");
           else
               System.out.println("Spozniles sie oferta wygasla");
        }
        else {
            System.out.println("Przykro mi :(");
        }

    }
}
