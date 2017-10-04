package pl.com.bottega.photostock.sales.infrastructure.Repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private static final Map<Long, Product> REPO;

    static {
        REPO = new HashMap<>();
        Set<String> tags = new HashSet<>();
        tags.add("Kotki");
        Product p1 = new Picture(1L, tags, Money.valueOf(10));
        Product p2 = new Picture(2L, tags, Money.valueOf(5));
        Product p3 = new Picture(3L, tags, Money.valueOf(15));

        REPO.put(1L, p1);
        REPO.put(2L, p2);
        REPO.put(3L, p3);

    }

    @Override
    public Product get(Long number) {
        if(!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        if ( REPO.containsKey(number))
            return Optional.of(REPO.get(number));
        else
            return Optional.empty();
    }

    @Override
    public void save(Product product) {
        REPO.put(product.getNumber(),product);

    }

    @Override
    public List<Product> find(Client client,Set<String> tags, Money from, Money to) {
        List<Product> result = new LinkedList<>();
        for( Product product : REPO.values()) {
            if (product instanceof Picture) {
                // product.getClass().equals(Picture.class) ==> to samo co wyzej
                Picture picture = (Picture) product;

                if (matchesCriteria(picture, client, tags, from, to))
                    result.add(picture);
            }
        }
            return result;

       /*         if(tags != null && !picture.hasTags(tags))
                    continue;
                Money price = picture.calculatePrice(client);

                if(from != null && from.gt(price))
                    continue;
                if (to != null && to.lt(price))
                    continue;

                result.add(picture);

            }
        }

        return result;
*/
        }


}
