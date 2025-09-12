package service;

import models.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MatchDao {
    private SessionFactory sf = HibernateUtils.getSessionFactory();

    public List<Match> getAll() {
        Transaction transaction = null;
        List<Match> matches = null;
        Session session = sf.openSession();
        try {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery("FROM Match", Match.class);
            matches = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return matches;
    }

    public List<String> getAllTableNames() {
        Transaction transaction = null;
        List<String> tableNames = new ArrayList<>();
        Session session = sf.openSession();

        try {
            session.doWork(connection -> {
                ResultSet resultSet = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
                while (resultSet.next()) {
                    tableNames.add(resultSet.getString("TABLE_NAME"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Закрытие сессии
        }

        return tableNames;
    }

    public List<Match> getAllMatches() {
        List<Match> matches = null;
        Session session = sf.openSession();
        try {
            matches = session.createQuery("FROM Match", Match.class).list();
           // matches = session.createNativeQuery("SELECT * FROM matches", Match.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return matches;
    }

}
