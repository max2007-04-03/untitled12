package ua.opnu.service.impl;

import ua.opnu.dao.PlanetDao;
import ua.opnu.entity.Planet;
import ua.opnu.service.PlanetService;

import java.util.regex.Pattern;

public class PlanetServiceImpl implements PlanetService {
    private static final Pattern PLANET_ID = Pattern.compile("^[A-Z0-9]+$");

    private final PlanetDao planetDao;

    public PlanetServiceImpl(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    @Override
    public Planet create(String id, String name) {
        validateId(id);
        validateName(name);

        Planet planet = new Planet();
        planet.setId(id.trim());
        planet.setName(name.trim());
        return planetDao.create(planet);
    }

    @Override
    public Planet findById(String id) {
        validateId(id);
        return planetDao.findById(id.trim());
    }

    @Override
    public Planet update(String id, String name) {
        validateId(id);
        validateName(name);

        Planet existing = planetDao.findById(id.trim());
        if (existing == null) {
            throw new IllegalArgumentException("Planet з id=" + id + " не знайдено");
        }

        existing.setName(name.trim());
        return planetDao.update(existing);
    }

    @Override
    public void delete(String id) {
        validateId(id);
        Planet existing = planetDao.findById(id.trim());
        if (existing == null) return;
        planetDao.delete(existing);
    }

    private static void validateId(String id) {
        if (id == null) throw new IllegalArgumentException("Planet id не може бути null");
        String trimmed = id.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("Planet id не може бути порожнім");
        if (trimmed.length() > 50) throw new IllegalArgumentException("Planet id має бути <= 50 символів");
        if (!PLANET_ID.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Planet id має відповідати ^[A-Z0-9]+$");
        }
    }

    private static void validateName(String name) {
        if (name == null) throw new IllegalArgumentException("Planet name не може бути null");
        String trimmed = name.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("Planet name не може бути порожнім");
        if (trimmed.length() > 500) throw new IllegalArgumentException("Planet name має бути <= 500 символів");
    }
}