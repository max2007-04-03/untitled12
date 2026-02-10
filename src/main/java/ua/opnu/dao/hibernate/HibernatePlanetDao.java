package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.PlanetDao;
import ua.opnu.entity.Planet;
import ua.opnu.util.HibernateUtil;

public class HibernatePlanetDao implements PlanetDao {

    @Override
    public Planet create(Planet planet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(planet);
            tx.commit();
            return planet;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
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
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Planet merged = session.merge(planet);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Planet planet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}