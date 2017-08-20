package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Offer {

    private Collection<Picture> items;
    private Client owner;


    public Offer ( Client owner, Collection<Picture> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
    }

    public boolean sameAs(Offer offer, Money tollerance) {
        return  false;
    }

    public int getItemsCount() {
        return items.size();
    }



    public Money getTotalCost() {

        Money totalCost = Money.ZERO;

        for ( Picture pictures : items) {
            totalCost= totalCost.add(pictures.calculatePrice(owner));
        }
        return totalCost;
    }

    public Collection<Picture> getItems() {

        return Collections.unmodifiableCollection(items);
    }
}
