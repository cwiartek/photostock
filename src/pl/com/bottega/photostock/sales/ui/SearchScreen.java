package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;

import java.io.IOException;
import java.util.*;

public class SearchScreen {

    private ProductCatalog productCatalog;
    private Scanner scanner;
    private AuthenticationManager authenticationManager;
    private Money money;

    public SearchScreen(Scanner scanner, AuthenticationManager authenticationManager,ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
    }

    public void show() throws IOException {
        System.out.println("Podaj kryteria wyszukiwania");
        System.out.println("Tagi: ");
        Set<String> tags = getTags();
        System.out.println("Cena od");
        Money priceFrom = getMoney();
        System.out.println("Cena do");
        Money priceTo = getMoney();

        List<Product> productList = productCatalog.find(authenticationManager.getClient(), tags, priceFrom, priceTo);

        for (Product product : productList)
            showProduct(product);

    }

    private void showProduct(Product product) {
        String productType = product instanceof Picture ? "OBRAZEK" : "CLIP";
        String tags = "";
        if ( product instanceof Picture)
            tags = ((Picture) product).getTags().toString();
        Money price = product.calculatePrice(authenticationManager.getClient());
        System.out.println(String.format(" %d - %s - %s %s", product.getNumber(),productType, tags,price));

    }

    public Set<String> getTags() {

        String line = scanner.nextLine();
        String[] tagsArray = line.split(" ");
        List<String> tagsList = Arrays.asList(tagsArray);
        Set<String> tags = new HashSet<>(tagsList);
        tags.remove("");
        return tags;
    }

    public Money getMoney() {
        String moneyString = scanner.nextLine();
        try {
            Integer moneyInteger = Integer.parseInt(moneyString);
            return Money.valueOf(moneyInteger);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
