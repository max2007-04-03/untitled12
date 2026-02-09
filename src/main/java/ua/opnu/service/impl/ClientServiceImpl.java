package ua.opnu.service.impl;

import ua.opnu.dao.ClientDao;
import ua.opnu.entity.Client;
import ua.opnu.service.ClientService;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client create(String name) {
        validateName(name);
        return clientDao.create(new Client(name.trim()));
    }

    @Override
    public Client findById(long id) {
        if (id <= 0) throw new IllegalArgumentException("Client id має бути > 0");
        return clientDao.findById(id);
    }

    @Override
    public Client update(long id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Client id має бути > 0");
        validateName(name);

        Client existing = clientDao.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Client з id=" + id + " не знайдено");
        }

        existing.setName(name.trim());
        return clientDao.update(existing);
    }

    @Override
    public void delete(long id) {
        if (id <= 0) throw new IllegalArgumentException("Client id має бути > 0");

        Client existing = clientDao.findById(id);
        if (existing == null) return; // або кидати exception — як домовитесь в бізнес-логіці
        clientDao.delete(existing);
    }

    private static void validateName(String name) {
        if (name == null) throw new IllegalArgumentException("Client name не може бути null");
        String trimmed = name.trim();
        if (trimmed.length() < 3) throw new IllegalArgumentException("Client name має містити мінімум 3 символи");
        if (trimmed.length() > 200) throw new IllegalArgumentException("Client name має бути <= 200 символів");
    }
}