import models.Match;
import service.MatchDao;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MatchDao md = new MatchDao();
       List<String>  tableNames= md.getAllTableNames();
        System.out.println(tableNames);
        List<Match> matches = md.getAllMatches();
        System.out.println(matches);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH/mm");
        for(Match match:matches){

            System.out.println(match.getId() +" : "+
                    match.getDate().format(formatter)+" : "+
                    match.getFirstPlayerId()+" : "+
                    match.getSecondPlayerId()+" : "+
                    match.getWinner());
        }
    }

}
