package pl.com.bottega.photostock.sales.model;

public interface Product {

    Long getNumber();
    Boolean isAvailable();
    Money calculatePrice(Client client);
    void reservedPer(Client client);
    void unreservedPer(Client client);
    void checkReservation(Client client, String format);
    void soldPer(Client client);

    default void ensureAvailable() {
        if(!isAvailable())
            throw new ProductNotAvailableException(this);
    }


    Money getPrice();

    Client getReservedBy();

    Client getOwner();
}
