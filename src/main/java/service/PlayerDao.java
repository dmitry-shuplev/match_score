package service;

import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    SessionFactory sessionFactory;

    public PlayerDao(SessionFactory sf) {
        sessionFactory = sf;
    }

    public List<Player> getAll(){
    List<Player> allPlayers = new ArrayList<>();


    return allPlayers;
    }

    public Player getById(int id) {
        Player player = null;
        try (Session session = sessionFactory.openSession()) {
            player = session.find(Player.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    public Player getByName(String name) {
        Transaction transaction = null;
        Player player = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Player p WHERE p.name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            player = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return player;
    }

    public void deleteByName(String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Player p WHERE p.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.executeUpdate();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public void crateNewPlayer(String name) {
        Transaction transaction = null;
        Player player = new Player();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            player.setName(name);
            session.persist(player);
        }
    }

    ;


    public String nameFormated(String inputName) {
        String cleanedInput = inputName.replaceAll("[^a-zA-zа-яА-ЯёЁ'\s]", "");
        String[] names = cleanedInput.split("\s+");
        String formatedString = "";
        for (String name : names) {
            if (!name.isEmpty()) {
                formatedString += name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
                        + " ";
            }
        }
        return formatedString.trim();
    }

    public boolean isCurrentPlayerExist(String inputName) {
        return getByName(inputName) != null;
    }

}
