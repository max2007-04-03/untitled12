package ua.opnu.dao;

import ua.opnu.entity.Ticket;

public interface TicketDao {
    Ticket create(Ticket ticket);
    Ticket findById(long id);
    Ticket update(Ticket ticket);
    void delete(Ticket ticket);
}