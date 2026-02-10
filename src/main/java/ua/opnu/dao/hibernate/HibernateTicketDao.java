package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.TicketDao;
import ua.opnu.entity.Ticket;
import ua.opnu.util.HibernateUtil;

public class HibernateTicketDao implements TicketDao {

    @Override
    public Ticket create(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(ticket);
            tx.commit();
            return ticket;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Ticket findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Ticket merged = session.merge(ticket);
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
    public void delete(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}