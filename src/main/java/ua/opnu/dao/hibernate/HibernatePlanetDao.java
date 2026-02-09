package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.PlanetDao;
import ua.opnu.entity.Planet;
import ua.opnu.util.HibernateUtil;

public class HibernatePlanetDao implements PlanetDao {

    @Override
    public Planet create(Planet planet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(planet);
            tx.commit();
            return planet;
        }
    }

    @Override
    public Planet findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    @Override
    public Planet update(Planet planet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Planet merged = (Planet) session.merge(planet);
            tx.commit();
            return merged;
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(planet);
            tx.commit();
        }
    }
}