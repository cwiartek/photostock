package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LightBox {

    private String name;
    private Client owner;
    private List<Product> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.name = name;
        this.owner = owner;
    }


    public void add ( Product product ){
        if (items.contains(product))
            throw new IllegalStateException("Already contains");
        if ( !product.isAvailable())
            throw new IllegalArgumentException("Picture is not available");
        items.add(product);

    }
    public void remove ( Product product) {
        if (!items.remove(product))
            throw new IllegalArgumentException("Does not contain");


    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return owner;
    }

    public Collection<Product> getItems() {
        return Collections.unmodifiableList(items);

    }


}
