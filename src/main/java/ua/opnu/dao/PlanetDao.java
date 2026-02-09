package ua.opnu.dao;

import ua.opnu.entity.Planet;

public interface PlanetDao {
    Planet create(Planet planet);
    Planet findById(String id);
    Planet update(Planet planet);
    void delete(Planet planet);
}