package ua.opnu;

import ua.opnu.dao.ClientDao;
import ua.opnu.dao.PlanetDao;
import ua.opnu.dao.TicketDao;
import ua.opnu.dao.hibernate.HibernateClientDao;
import ua.opnu.dao.hibernate.HibernatePlanetDao;
import ua.opnu.dao.hibernate.HibernateTicketDao;
import ua.opnu.entity.Ticket;
import ua.opnu.service.ClientService;
import ua.opnu.service.PlanetService;
import ua.opnu.service.TicketService;
import ua.opnu.service.impl.ClientServiceImpl;
import ua.opnu.service.impl.PlanetServiceImpl;
import ua.opnu.service.impl.TicketServiceImpl;

public class DemoRunner {
    public void run() {
        ClientDao clientDao = new HibernateClientDao();
        PlanetDao planetDao = new HibernatePlanetDao();
        TicketDao ticketDao = new HibernateTicketDao();

        ClientService clientService = new ClientServiceImpl(clientDao);
        PlanetService planetService = new PlanetServiceImpl(planetDao);
        TicketService ticketService = new TicketServiceImpl(ticketDao, clientDao, planetDao);

        // 1) CREATE (використовуємо дані з V2__populate_db.sql: client_id=1, планети EARTH/MARS)
        Ticket created = ticketService.create(1L, "EARTH", "MARS");
        System.out.println("Ticket created, id=" + created.getId());

        // 2) READ
        Ticket loaded = ticketService.findById(created.getId());
        System.out.println("Ticket loaded, id=" + loaded.getId()
                + ", createdAt=" + loaded.getCreatedAt());

        // 3) DELETE
        ticketService.delete(created.getId());
        System.out.println("Ticket deleted, id=" + created.getId());

        // (необов'язково) перевірка що видалено
        Ticket afterDelete = ticketService.findById(created.getId());
        System.out.println("After delete, findById result = " + afterDelete);
    }
}