import models.Match;
import service.MatchDao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MatchDao md = new MatchDao();
       List<String>  tableNames= md.getAllTableNames();
        System.out.println(tableNames);
        List<Match> matches = md.getAllMatches();
        System.out.println(matches);
    }

}
