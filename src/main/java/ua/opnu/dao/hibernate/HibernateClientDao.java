package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.ClientDao;
import ua.opnu.entity.Client;
import ua.opnu.util.HibernateUtil;

public class HibernateClientDao implements ClientDao {

    @Override
    public Client create(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
            return client;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Client findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }
    }

    @Override
    public Client update(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Client merged = session.merge(client);
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
    public void delete(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}