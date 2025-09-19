package service;

import models.Match;
import models.MatchWebDto;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MatchDao {
    private SessionFactory SessionFactory = HibernateUtils.getSessionFactory();

    public List<String> getAllTableNames() {
        Transaction transaction = null;
        List<String> tableNames = new ArrayList<>();
        Session session = SessionFactory.openSession();
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
        Session session = SessionFactory.openSession();

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
        Session session = SessionFactory.openSession();
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

}
