import models.Match;
import models.MatchWebDto;
import service.MatchDao;
import service.MatchesDao;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MatchesDao matchesDao = new MatchesDao();
        List<Match> matches = matchesDao.getAllMatches();
        System.out.println(matches.toString());
    }

}
