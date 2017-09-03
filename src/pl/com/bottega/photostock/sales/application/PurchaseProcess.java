package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.Repositories.PurchaseRepository;
import pl.com.bottega.photostock.sales.model.Repositories.ReservationRepository;

import java.util.Collection;

public class PurchaseProcess {

    private ClientRepository clientRepository;
    private ReservationRepository reservationRepository;
    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;
    private static final Money TOLLERANCE = Money.valueOf(10);

    public PurchaseProcess(ClientRepository repository, ReservationRepository reservationRepository, ProductRepository productRepository,
                           PurchaseRepository purchaseRepository) {
        this.clientRepository = repository;
        this.reservationRepository= reservationRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public String createReservation(String clientNumber) {
        Client client = clientRepository.get(clientNumber);
        Reservation reservation = new Reservation(client);
        reservationRepository.save(reservation);
        return reservation.getNumber();
    }

    public void add(String reservationNumber, Long productNumber) {
        Reservation reservation = reservationRepository.get(reservationNumber);
        Product product = productRepository.get(productNumber);
        reservation.add(product);
        reservationRepository.save(reservation);
        productRepository.save(product);
    }

    public Offer calculateOffer(String reservationNumber) {

        Reservation reservation = reservationRepository.get(reservationNumber);
        Offer offer = reservation.generateOffer();
        return offer;
    }

    public PurchaseStatus confirm(String reservationNumber, Offer clientOffer) {
        Offer currentOffer = calculateOffer(reservationNumber);
        if (currentOffer.sameAs(clientOffer, TOLLERANCE)) {
            Client client = currentOffer.getOwner();
            Money offerCost = clientOffer.getTotalCost();
            if (client.canAfford(offerCost)) {
                Purchase purchase = clientOffer.purchase();
                purchaseRepository.save(purchase);
                clientRepository.save(client);
                clientRepository.save(client);
                Collection<Product> products = clientOffer.getItems();
                for (Product product : products)
                    productRepository.save(product);
                return PurchaseStatus.SUCCESS;
            } else
                return PurchaseStatus.NO_ENOUGH_FOUNDS;
            }

         else return PurchaseStatus.OFFER_MISSMATCH;

        }
    }