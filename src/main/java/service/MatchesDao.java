package service;

import models.Match;
import models.MatchWebDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class MatchesDao {
    SessionFactory sessionFactory;

    public MatchesDao(SessionFactory sf){
        this.sessionFactory = sf;
    }

    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            String hql = "From Match";
            matches = session.createQuery(hql, Match.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        matches.sort((m1, m2) -> m2.getDate().compareTo(m1.getDate()));
        return matches;
    }

    public List<Match> getMatchesByPlayer(String p){
        List<Match> matches = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            String hql = "FROM Match m WHERE m.firstPlayerId = :playerId OR m.secondPlayerId = :playerId";
            Query<Match> query = session.createQuery(hql, Match.class);
            int playerId = new PlayerDao(sessionFactory).getByName(p).getId();
            query.setParameter("playerId", playerId);
            matches = query.getResultList();
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    return matches;
    }

}
