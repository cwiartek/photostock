package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

public class Picture {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedBy, owner;


    public Picture ( Long number, Set<String> tags, Money price) {
        this(number,tags,price,true);

    }

    public Picture ( Long number, Set<String> tags, Money price, Boolean acitve) {
        this.number = number;
        this.tags = new HashSet<>(tags);
        this.price = price;
        this.active = acitve;

    }


    public  Money calculatePrice(Client client) {
        switch (client.getStatus()) {
            case SILVER:
                return price.percent(95);
            case GOLD:
                return price.percent(90);
            case PLATINUM:
                return price.percent(85);
        }
        return price;
    }

    public boolean isAvailable() {

        return active && reservedBy == null;
    }
    public void reservedPer(Client client) {
        if (!isAvailable())
            throw new IllegalStateException("Product is not available");
        reservedBy = client;

    }

    public void  unreservedPer(Client client) {
        if (owner != null)
            throw new IllegalStateException("Product is already purchased");
        checkReservation(client, String.format("Product is not reserved by %s", client));
        reservedBy = null;

    }

    private void checkReservation(Client client, String format) {
        if (reservedBy == null || !reservedBy.equals(client))
            throw new IllegalStateException(format);
    }

    public void soldPer(Client client) {
        checkReservation(client, String.format("Product is  not reserved by %s", client));

        owner = client;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public Long getNumber() {
        return number;
    }
}
