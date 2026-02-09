package ua.opnu.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.opnu.dao.TicketDao;
import ua.opnu.entity.Ticket;
import ua.opnu.util.HibernateUtil;

public class HibernateTicketDao implements TicketDao {

    @Override
    public Ticket create(Ticket ticket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(ticket);
            tx.commit();
            return ticket;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Ticket merged = (Ticket) session.merge(ticket);
            tx.commit();
            return merged;
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(ticket);
            tx.commit();
        }
    }
}