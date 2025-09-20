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

    public String nameFormated(String inputName) {
        String cleanedInput = inputName.replaceAll("[^a-zA-zа-яА-ЯёЁ'\s]", "");
        String[] names = cleanedInput.split("\s+");
        String formatedString = "";
        for (String name : names) {
            if (!name.isEmpty()) {
                formatedString = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
                        + " ";
            }
        }
        return formatedString.trim();
    }
}
