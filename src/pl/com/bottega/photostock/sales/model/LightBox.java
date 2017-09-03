package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class LightBox {

    private String name;
    private Client owner;
    private List<Picture> items = new LinkedList<>();
    private String number;

    public LightBox(Client owner, String name) {
        this.name = name;
        this.owner = owner;
        this.number = UUID.randomUUID().toString();
    }


    public void add ( Picture picture ){
        if (items.contains(picture))
            throw new IllegalStateException("Already contains");
        picture.ensureAvailable();
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


    public String getNumber() {
        return number;
    }


    public List<Picture> getPicture(Set<Long> pictureNumbers) {
        List<Picture> result = new LinkedList<>();
        for( Picture pic : items)
            if(pictureNumbers.contains(pic.getNumber()))
                result.add(pic);
        return result;

    }
}
