package ua.opnu.service.impl;

import ua.opnu.dao.ClientDao;
import ua.opnu.dao.PlanetDao;
import ua.opnu.dao.TicketDao;
import ua.opnu.entity.Client;
import ua.opnu.entity.Planet;
import ua.opnu.entity.Ticket;
import ua.opnu.service.TicketService;

public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;
    private final ClientDao clientDao;
    private final PlanetDao planetDao;

    public TicketServiceImpl(TicketDao ticketDao, ClientDao clientDao, PlanetDao planetDao) {
        this.ticketDao = ticketDao;
        this.clientDao = clientDao;
        this.planetDao = planetDao;
    }

    @Override
    public Ticket create(long clientId, String fromPlanetId, String toPlanetId) {
        if (clientId <= 0) throw new IllegalArgumentException("clientId має бути > 0");
        if (fromPlanetId == null || fromPlanetId.trim().isEmpty()) {
            throw new IllegalArgumentException("fromPlanetId не може бути порожнім");
        }
        if (toPlanetId == null || toPlanetId.trim().isEmpty()) {
            throw new IllegalArgumentException("toPlanetId не може бути порожнім");
        }

        String fromId = fromPlanetId.trim();
        String toId = toPlanetId.trim();

        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("fromPlanetId та toPlanetId не можуть бути однаковими");
        }

        Client client = clientDao.findById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client з id=" + clientId + " не знайдено");
        }

        Planet fromPlanet = planetDao.findById(fromId);
        if (fromPlanet == null) {
            throw new IllegalArgumentException("Planet (from) з id=" + fromId + " не знайдено");
        }

        Planet toPlanet = planetDao.findById(toId);
        if (toPlanet == null) {
            throw new IllegalArgumentException("Planet (to) з id=" + toId + " не знайдено");
        }

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        return ticketDao.create(ticket);
    }

    @Override
    public Ticket findById(long id) {
        if (id <= 0) throw new IllegalArgumentException("Ticket id має бути > 0");
        return ticketDao.findById(id);
    }

    @Override
    public void delete(long id) {
        if (id <= 0) throw new IllegalArgumentException("Ticket id має бути > 0");
        Ticket existing = ticketDao.findById(id);
        if (existing == null) return;
        ticketDao.delete(existing);
    }
}