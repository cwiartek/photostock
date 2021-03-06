package pl.com.bottega.photostock.sales.model.Repositories;

import pl.com.bottega.photostock.sales.model.Client;

import java.util.Optional;

public interface ClientRepository {

    Client get(String number);

    void save(Client client);

    Optional<Client> getByLogin(String login);
}
