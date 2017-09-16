package pl.com.bottega.photostock.sales.infrastructure.Repositories;

import pl.com.bottega.photostock.sales.model.Address;
import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.Repositories.LightboxRepository;
import pl.com.bottega.photostock.sales.model.VIPClient;

import java.util.*;

public class InMemoryLightboxRepository implements LightboxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();


    @Override
    public void save(LightBox lightBox) {
        REPO.put(lightBox.getNumber(),lightBox);

    }

    @Override
    public LightBox get(String number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException(String.format("No lightbox %s found", number));
        return REPO.get(number);

    }
    @Override
    public List<LightBox> getClientLightBoxes(String clientNumber) {
        List<LightBox> lboxys = new LinkedList<>();
        for ( LightBox lightBox : REPO.values())
            if(lightBox.getOwner().getNumber().equals(clientNumber))
                lboxys.add(lightBox);
        return lboxys;
   }
}
