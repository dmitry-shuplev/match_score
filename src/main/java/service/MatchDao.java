package service;

import models.Match;
import models.MatchProcessDto;
import models.MatchWebDto;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MatchDao {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public List<String> getAllTableNames() {
        Transaction transaction = null;
        List<String> tableNames = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            session.doWork(connection -> {
                ResultSet resultSet = connection.getMetaData().getTables(null, null,
                        "%", new String[]{"TABLE"});
                while (resultSet.next()) {
                    tableNames.add(resultSet.getString("TABLE_NAME"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return tableNames;
    }

    public List<Match> getAll() {
        List<Match> matches = null;
        Session session = sessionFactory.openSession();

        try {
            matches = session.createQuery("FROM Match", Match.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return matches;
    }

    public List<MatchWebDto> getAllWebDto() {
        List<Match> matches = getAll();
        Session session = sessionFactory.openSession();
        List<MatchWebDto> matchesWebDto = new ArrayList<>();

        for (Match match : matches) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            try {
                String date = match.getDate().format(formatter);
                String firstPlayerName = session.find(Player.class, match.getFirstPlayerId()).getName();
                String secondPlayerName = session.find(Player.class, match.getSecondPlayerId()).getName();
                String winnerName = session.find(Player.class, match.getWinner()).getName();

                MatchWebDto matchDto = new MatchWebDto(
                        (date != null) ? date : "Unknown",
                        (firstPlayerName != null) ? firstPlayerName : "Unknown",
                        (secondPlayerName != null) ? secondPlayerName : "Unknown",
                        (winnerName != null) ? winnerName : "Unknowed");
                matchesWebDto.add(matchDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.close();
        return matchesWebDto;
    }

    public void saveMatch(Match match){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Match getMatchFromMatchProcessDto(MatchProcessDto matchDto){
        Match match = new Match();
        PlayerDao pd= new PlayerDao();
        match.setFirstPlayerId(pd.getByName(matchDto.getFirstPlayerName()).getId());
        match.setSecondPlayerId(pd.getByName(matchDto.getSecondPlayerName()).getId());
        match.setWinner(pd.getByName(matchDto.getWinnreName()).getId());
        match.setDate(LocalDateTime.now()) ;
        return match;
    }

}
