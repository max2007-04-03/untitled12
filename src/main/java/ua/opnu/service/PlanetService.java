package ua.opnu.service;

import ua.opnu.entity.Planet;

public interface PlanetService {
    Planet create(String id, String name);

    Planet findById(String id);

    Planet update(String id, String name);

    void delete(String id);
}