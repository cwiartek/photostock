package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {

    private List<Picture> items;
    private Client owner;


    public Offer ( Client owner, Collection<Picture> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
        this.items.sort(new Comparator<Picture>() {
            @Override
            public int compare(Picture o1, Picture o2) {
                return o2.calculatePrice(owner).compareTo(o2.calculatePrice(owner));
            }
        });

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
