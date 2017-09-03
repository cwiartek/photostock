package pl.com.bottega.photostock.sales.model.Repositories;

import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;

public interface LightboxRepository {

    void save(LightBox lightBox);

    LightBox get (String number);

    List<LightBox> getClientLightBoxes(String clientNumber);


}
