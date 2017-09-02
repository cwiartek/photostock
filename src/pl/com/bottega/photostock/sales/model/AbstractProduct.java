package pl.com.bottega.photostock.sales.model;

public abstract class AbstractProduct implements Product {

    private Long number;
    private Money price;
    private Boolean active;
    private Client reservedBy, owner;

    public AbstractProduct(Long number, Money price, Boolean active) {
        this.number = number;
        this.price = price;
        this.active = active;
    }

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public Boolean isAvailable() {
        return active && reservedBy == null;
    }

    @Override
    public Money calculatePrice(Client client) {
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

    @Override
    public void reservedPer(Client client) {
        ensureAvailable();
        reservedBy = client;

    }

    @Override
    public void unreservedPer(Client client) {
        if (owner != null)
            throw new IllegalStateException("Product is already purchased");
        checkReservation(client, String.format("Product is not reserved by %s", client));
        reservedBy = null;

    }

    @Override
    public void checkReservation(Client client, String format) {
        if (reservedBy == null || !reservedBy.equals(client))
            throw new IllegalStateException(format);

    }

    @Override
    public void soldPer(Client client) {
        checkReservation(client, String.format("Product is  not reserved by %s", client));

        owner = client;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractProduct product = (AbstractProduct) o;

        return number.equals(product.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
