package service;

import models.Match;
import models.MatchWebDto;
import org.hibernate.SessionFactory;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MatchesHadler {
    SessionFactory sessionFactory;
    PlayerDao playerDao;

    public MatchesHadler(SessionFactory sf) {
        this.sessionFactory = sf;
        playerDao = new PlayerDao(sessionFactory);
    }

    public List<MatchWebDto> convertToMatchWebDto(List<Match> matches) {
        MatchWebDto matchWebDto;
        List<MatchWebDto> matchesWebDto = new ArrayList<>();
        for (Match match : matches) {
            matchWebDto = new MatchWebDto(
                    match.getDate().format(DateTimeFormatter.ofPattern("HH:mm|dd.MM")),
                    playerDao.getById(match.getFirstPlayerId()).getName(),
                    playerDao.getById(match.getSecondPlayerId()).getName(),
                    playerDao.getById(match.getWinner()).getName()
            );
            matchesWebDto.add(matchWebDto);
        }
        return matchesWebDto;
    }

}
