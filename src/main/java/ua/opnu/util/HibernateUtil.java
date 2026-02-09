package ua.opnu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.flywaydb.core.Flyway;
import ua.opnu.entity.Client;
import ua.opnu.entity.Planet;
import ua.opnu.entity.Ticket;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                System.out.println("Starting Flyway migration...");
                Flyway flyway = Flyway.configure()
                        .dataSource("jdbc:h2:./spacetravel", "sa", "")
                        .locations("classpath:db/migration")
                        .load();
                flyway.migrate();
                System.out.println("Flyway migration completed.");

                System.out.println("Building Hibernate SessionFactory...");
                sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(Client.class)
                        .addAnnotatedClass(Planet.class)
                        .addAnnotatedClass(Ticket.class)
                        .buildSessionFactory();
                System.out.println("SessionFactory built successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error initializing Hibernate/Flyway", e);
            }
        }
        return sessionFactory;
    }
}