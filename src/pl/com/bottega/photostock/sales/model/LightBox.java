package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LightBox {

    private String name;
    private Client owner;
    private List<Picture> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.name = name;
        this.owner = owner;
    }


    public void add ( Picture picture ){
        if (items.contains(picture))
            throw new IllegalStateException("Already contains");
        if ( !picture.isAvailable())
            throw new IllegalArgumentException("Picture is not available");
        items.add(picture);

    }
    public void remove ( Picture picture) {
        if (!items.remove(picture))
            throw new IllegalArgumentException("Does not contain");


    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return owner;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableList(items);

    }


}
