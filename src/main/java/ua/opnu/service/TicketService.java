package ua.opnu.service;

import ua.opnu.entity.Ticket;

public interface TicketService {
    Ticket create(long clientId, String fromPlanetId, String toPlanetId);

    Ticket findById(long id);

    Ticket update(long ticketId, long clientId, String fromPlanetId, String toPlanetId);

    void delete(long id);
}