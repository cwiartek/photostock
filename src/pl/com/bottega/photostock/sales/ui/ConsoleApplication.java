package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryPictureRepository;
import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Set;

import static pl.com.bottega.photostock.sales.model.ClientStatus.VIP;

public class ConsoleApplication {


    public static void main(String[] args) {

        PictureRepository repository = new InMemoryPictureRepository();
        Picture p1 = repository.get(1L);
        Picture p2 = repository.get(2L);
        Picture p3 = repository.get(3L);

        Client client = new Client("Jan Nowak" , new Adress( " ul. Polnocna 11", "Poland", "Lublin", "20-429" ));

        client.recharge(Money.valueOf(1000000));

        Reservation reservation = new Reservation(client);

        LightBox l = new LightBox(client, "Kotki");
        l.add(p1);
        l.add(p2);
        l.add(p3);
        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(l);

        reservation.add(p1);
        reservation.add(p2);
        reservation.add(p3);

        System.out.println(String.format("W rezerwacji jest %d produktow", reservation.getItemsCount()));

        int count = reservation.getItemsCount();

        Offer offer = reservation.generateOffer();

        Money offerTotalCost = offer.getTotalCost();
        boolean canAfford = client.canAfford(offerTotalCost);

        if (canAfford) {
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(offerTotalCost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilosc zakupionych zdjec:  %d, calkowity koszt: %s" , offer.getItemsCount(),offerTotalCost));


        }
        else
            System.out.println("Sorry nie stac Cie");



    }
}
