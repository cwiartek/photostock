package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {

    private List<Product> items;
    private Client owner;
    private Client client;
    private List<Product> products;


    public Offer ( Client owner, Collection<Product> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
        this.items.sort((o1, o2) -> o2.calculatePrice(owner).compareTo(o2.calculatePrice(owner)));

    }


    public boolean sameAs(Offer offer, Money tollerance)
    {
        return getTotalCost().substract(offer.getTotalCost()).abs().lte(tollerance);
    }

    public int getItemsCount() {
        return items.size();
    }


    public Money getTotalCost() {

        Money totalCost = Money.ZERO;

        for ( Product p : items) {
            totalCost= totalCost.add(p.calculatePrice(owner));
        }
        return totalCost;
    }

    public Collection<Product> getItems() {

        return Collections.unmodifiableCollection(items);
    }
    public Purchase purchase() {
        Money cost = getTotalCost();
        Purchase purchase = new Purchase(owner, items);
        owner.charge(cost, String.format("Purchase number %s " ,purchase.getNumber()));
        return purchase;
    }

    public Client getOwner() {
        return owner;
    }

    public List<Product> getProducts() {
        return products;
    }
}
