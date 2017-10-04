package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ProductCatalog {

    private ProductRepository repository;

    public ProductCatalog(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> find(Client client, Set<String> tags, Money from, Money to) throws IOException {
        return repository.find(client,tags,from,to);

        // find(null, null, null)
        // find(jakies tag, null, null)
        //find(jakies tagi, null, kwota)
    }
}
