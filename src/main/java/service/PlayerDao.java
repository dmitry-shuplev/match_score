package service;

import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.processing.SQL;
import utils.HibernateUtils;

import java.sql.SQLException;

public class PlayerDao {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public Player getById(int id) {
        Player player = new Player();
        try {
            Session session = sessionFactory.openSession();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
        return player;

    }
}
