package pl.com.bottega.photostock.sales.infrastructure.Repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.Repositories.ClientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryClientRepository implements ClientRepository {

    private static final Map<String, Client> REPO;

    static {
        REPO = new HashMap<>();
        Client c1 = new VIPClient("Jan Nowak" , new Address( " ul. Polnocna 11", "Poland", "Lublin", "20-429"));
        Client c2 = new VIPClient("Jan Nowak2" , new Address( " ul. Polnocna 10", "Poland", "Lublin", "20-429"));
        Client c3 = new VIPClient("Jan Nowak3" , new Address( " ul. Polnocna 11", "Poland", "Lublin", "20-429"));

        REPO.put(c1.getNumber(),c1);
        REPO.put(c2.getNumber(),c2);
        REPO.put(c3.getNumber(),c3);

    }


    @Override
    public Client get(String number) {
        if(!REPO.containsKey(number))
            throw new IllegalArgumentException(String.format("No client %s found",number));
        return REPO.get(number);
    }

    @Override
    public void save(Client client) {
        REPO.put(client.getNumber(),client);

    }

    @Override
    public Optional<Client> getByLogin(String login) {

        for ( Client client : REPO.values()) {
            if ( client.getName().toLowerCase().equals(login.toLowerCase())){
                return Optional.of(client);
            }
        }
    return Optional.empty();
    }
}
