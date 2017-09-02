package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

public interface ProductRepository {

    // pobiera obiekt po identyfikatorze
    Picture get(Long number);

    Optional<Picture> getOptional(Long number);


    // zapis nowego lub aktualizacja isteniejacego
    void save(Picture picture);
}
