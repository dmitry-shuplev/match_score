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
    private final int NOTES_IN_PAGE = 15;


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

    public List<MatchWebDto> paginate(List<MatchWebDto> matches, int currentPage) {
        List<MatchWebDto> paginateMatches = new ArrayList<>();
        int starteNoteNumber = NOTES_IN_PAGE * currentPage - NOTES_IN_PAGE;
        int finishNoteNumber = starteNoteNumber + NOTES_IN_PAGE - 1;
        if (finishNoteNumber > matches.size()-1) finishNoteNumber = matches.size()-1;
        for (int i = starteNoteNumber; i <= finishNoteNumber; i++) {
        paginateMatches.add(matches.get(i));
        }
        return paginateMatches;
    }

}
