package pl.com.bottega.photostock.sales.infrastructure
        ;

import pl.com.bottega.photostock.sales.application.LightboxManagment;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.InMemoryLightboxRepository;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.Repositories.InMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.LightboxRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ReservationRepository;
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
        ProductRepository productRepository = new InMemoryProductRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        AuthenticationManager authenticationManager = new AuthenticationManager(clientRepository);
        LightboxManagment lightBoxManagement = new LightboxManagment(lightBoxRepository, clientRepository,
                                                        productRepository, reservationRepository);
        LightBoxManagmentsScreen lightBoxManagementScreen = new LightBoxManagmentsScreen(scanner, lightBoxManagement,authenticationManager);
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(scanner, authenticationManager, productCatalog);
        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagementScreen, searchScreen);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(scanner, authenticationManager);

        authenticationScreen.show();
        mainScreen.show();

    }
}
