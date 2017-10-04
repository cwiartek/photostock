package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.infrastructure.Repositories.CSVProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;

public class CSVProductRepositoryTest {

    public static void main(String[] args) {
        ProductRepository productRepository = new CSVProductRepository("D:\\product.csv",new InMemoryClientRepository());
        Product product = productRepository.get(3L);
        System.out.println(product.getNumber());
    }
}
