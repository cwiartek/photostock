package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.LightboxRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ReservationRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LightboxManagment {

    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private LightboxRepository lightboxRepository;
    private PurchaseProcess purchaseProcess;
    private ReservationRepository reservationRepository;

    public LightboxManagment(LightboxRepository lightboxRepository,ClientRepository clientRepository,ProductRepository productRepository, ReservationRepository reservationRepository)
                             {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.lightboxRepository = lightboxRepository;
        this.purchaseProcess = purchaseProcess;
        this.reservationRepository = reservationRepository;
    }


    public String createLightbox(String clientnumber, String name) {
        Client client = clientRepository.get(clientnumber);
        LightBox lightBox = new LightBox(client,name);
        lightboxRepository.save(lightBox);
        return lightBox.getNumber();

    }

    public void add(String lightBoxNumber,Long productNumber) {

        Product product = productRepository.get(productNumber);
        if (!(product instanceof Picture))
            throw new IllegalArgumentException();

        LightBox lightBox = lightboxRepository.get(lightBoxNumber);
        Picture picture = (Picture) product;
        lightBox.add(picture);
        lightboxRepository.save(lightBox);

    }

    public void reserve(String lightBoxNumber, Set<Long> pictureNumbers,  String reservationNumber) throws IOException {

        LightBox lightBox = lightboxRepository.get(lightBoxNumber);
        Reservation reservation = reservationRepository.get(reservationNumber);
        List<Picture> pictures = lightBox.getPicture(pictureNumbers);
        if( pictureNumbers.size() != pictures.size())
            throw new IllegalArgumentException("Invalid product numbers");
        for (Picture picture : pictures)
            picture.ensureAvailable();
        for (Picture picture : pictures) {
            reservation.add(picture);
            productRepository.save(picture);
        }
        reservationRepository.save(reservation);
    }

    public List<LightBox> getLightBoxes(String clientNumber) {

        return lightboxRepository.getClientLightBoxes(clientNumber);

    }
}
