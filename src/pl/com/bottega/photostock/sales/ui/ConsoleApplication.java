package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.*;

public class ConsoleApplication {


    public static void main(String[] args) {

        Picture p1 = new Picture();
        Picture p2 = new Picture();
        Picture p3 = new Picture();

        Client client = new Client();

        Reservation reservation = new Reservation();

        reservation.add(p1);
        reservation.add(p2);
        reservation.add(p3);

        reservation.add(p1);
        reservation.add(p2);
        reservation.add(p3);


        System.out.println(String.format("W rezerwacji jest %d produktow", reservation.getItemsCount()));

        int count = reservation.getItemsCount();

        Offer offer = reservation.generateOffer();

        Money offerTotalCost = offer.getTotalCost();
        boolean canAfford = client.canAfford(offerTotalCost);

        if (canAfford) {
            Purchase purchase = new Purchase();
            client.charge(offerTotalCost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilosc zakupionych zdjec:  %d, calkowity koszt: %s" , offer.getItemsCount(),offerTotalCost));


        }
        else
            System.out.println("Sorry nie stac Cie");



    }
}
