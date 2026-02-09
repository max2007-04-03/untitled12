package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.ClientDao;
import ua.opnu.entity.Client;
import ua.opnu.util.HibernateUtil;

public class HibernateClientDao implements ClientDao {

    @Override
    public Client create(Client client) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
            return client;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Client merged = (Client) session.merge(client);
            tx.commit();
            return merged;
        }
    }

    @Override
    public void delete(Client client) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        }
    }
}