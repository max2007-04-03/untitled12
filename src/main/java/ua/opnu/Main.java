package ua.opnu;

import ua.opnu.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        try {
            HibernateUtil.getSessionFactory();
            new DemoRunner().run();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}