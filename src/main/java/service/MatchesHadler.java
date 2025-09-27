package service;

import models.Match;
import models.MatchWebDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MatchesHadler {

    public List<MatchWebDto> convertToMatchWebDto(List<Match> matches) {
        MatchWebDto matchWebDto;
        PlayerDao pd = new PlayerDao();
        List<MatchWebDto> matchesWebDto = new ArrayList<>();
        for (Match match : matches) {
          matchWebDto = new MatchWebDto(
                   match.getDate().format(DateTimeFormatter.ofPattern("HH:mm|dd.MM")),
                  pd.getById(match.getFirstPlayerId()).getName(),
                  pd.getById(match.getSecondPlayerId()).getName(),
                  pd.getById(match.getWinner()).getName()
          );
          matchesWebDto.add(matchWebDto);
        }
        return matchesWebDto;
    }

}
