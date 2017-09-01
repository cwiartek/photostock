package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryPictureRepository;
import pl.com.bottega.photostock.sales.model.*;

import java.util.HashMap;
import java.util.Map;

public class ConsoleApplication {


    public static void main(String[] args) {

        PictureRepository repository = new InMemoryPictureRepository();
        Picture p1 = repository.get(1L);
        Picture p2 = repository.get(2L);
        Picture p3 = repository.get(3L);

        Client client = new Client("Jan Nowak" , new Address( " ul. Polnocna 11", "Poland", "Lublin", "20-429" ));

        client.recharge(Money.valueOf(1000000));

        Reservation reservation = new Reservation(client);



        Map<String, Double> rates = new HashMap<>();

        rates.put("USD", 3.7020);
        rates.put("EUR", 4.0345);
        rates.put("GBP", 4.6450);
        rates.put("CHF", 3.7326);

        CurrencyConverter c = new CurrencyConverter("PLN",rates );

        LightBox l = new LightBox(client, "Kotki");
        l.add(p1);
        l.add(p2);
        l.add(p3);
        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(l);

        reservation.add(p1);
        reservation.add(p2);
        presenter.show(l);
        reservation.add(p3);

        System.out.println(String.format("W rezerwacji jest %d produktow", reservation.getItemsCount()));

        Offer offer = reservation.generateOffer();

        Money cost = offer.getTotalCost();

        System.out.println(client.balance());

        System.out.println(Money.valueOf(1, "USD").convert("PLN", 3.6));
        System.out.println(c.convert(Money.valueOf(1, "USD")) );
        System.out.println(c.convert(Money.valueOf(5, "PLN"), "USD"));
        System.out.println(c.convert(Money.valueOf(1, "EUR"), "USD") );

        if (client.canAfford(cost)) {
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(cost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilosc zakupionych zdjec:  %d, calkowity koszt: %s" , offer.getItemsCount(),cost));


        }
        else
            System.out.println("Sorry nie stac Cie");



    }
}
