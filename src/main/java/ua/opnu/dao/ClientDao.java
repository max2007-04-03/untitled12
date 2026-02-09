package ua.opnu.dao;

import ua.opnu.entity.Client;

public interface ClientDao {
    Client create(Client client);
    Client findById(long id);
    Client update(Client client);
    void delete(Client client);
}