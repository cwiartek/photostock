package pl.com.bottega.photostock.sales.model.Repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    // pobiera obiekt po identyfikatorze
    Product get(Long number);

    Optional<Product> getOptional(Long number);


    // zapis nowego lub aktualizacja isteniejacego
    void save(Product product) throws IOException;


    List<Product> find(Client client, Set<String> tags, Money from, Money to) throws IOException;

    default boolean matchesCriteria(Picture picture, Client client, Set<String> tags, Money from, Money to) {
        if (tags != null && !picture.hasTags(tags))
            return false;

        Money price = picture.calculatePrice(client);

        if (from != null && from.gt(price))
            return false;

        if (to != null && to.lt(price))
            return false;

        return true;
    }
}



