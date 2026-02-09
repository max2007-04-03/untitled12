package ua.opnu.service;

import ua.opnu.entity.Client;

public interface ClientService {
    Client create(String name);
    Client findById(long id);
    Client update(long id, String name);
    void delete(long id);
}
