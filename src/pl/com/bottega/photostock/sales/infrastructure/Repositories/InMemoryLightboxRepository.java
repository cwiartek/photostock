package pl.com.bottega.photostock.sales.infrastructure.Repositories;

import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Repositories.LightboxRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
