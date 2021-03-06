package pl.com.bottega.photostock.sales.infrastructure
        ;

import pl.com.bottega.photostock.sales.application.LightboxManagment;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.*;
import pl.com.bottega.photostock.sales.model.Repositories.*;
import pl.com.bottega.photostock.sales.ui.*;

import java.util.Scanner;

public class PhotoStockApp {



    public static void main(String[] args) {
        new PhotoStockApp().start();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        LightboxRepository lightBoxRepository = new InMemoryLightboxRepository();
        ClientRepository clientRepository = new InMemoryClientRepository();
        //ProductRepository productRepository = new InMemoryProductRepository();
        String CSVProductRepository = "D:\\product.csv";
        ProductRepository productRepository = new CSVProductRepository(CSVProductRepository,clientRepository);
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();
        AuthenticationManager authenticationManager = new AuthenticationManager(clientRepository);
        LightboxManagment lightBoxManagement = new LightboxManagment(lightBoxRepository, clientRepository,
                productRepository, reservationRepository);
        AddProductToLightboxScreen addProductToLightboxScreen = new AddProductToLightboxScreen(lightBoxManagement, scanner);
        PurchaseProcess purchaseProcess = new PurchaseProcess(clientRepository,reservationRepository,productRepository,purchaseRepository);
        PurchaseLightBoxScreen purchaseLightBoxScreen = new PurchaseLightBoxScreen(lightBoxManagement,purchaseProcess,scanner);
        LightBoxManagmentsScreen lightBoxManagementScreen = new LightBoxManagmentsScreen(scanner, lightBoxManagement,authenticationManager,addProductToLightboxScreen,purchaseLightBoxScreen);
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(scanner, authenticationManager, productCatalog);
        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagementScreen, searchScreen);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(scanner, authenticationManager);

        authenticationScreen.show();
        mainScreen.show();

    }
}
