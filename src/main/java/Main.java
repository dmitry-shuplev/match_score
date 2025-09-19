import models.Match;
import models.MatchWebDto;
import service.MatchDao;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//       List<String>  tableNames= md.getAllTableNames();
//        System.out.println(tableNames);
        List<MatchWebDto> matches = new MatchDao().getAllWebDto();
        for(MatchWebDto match:matches){
            System.out.println(match.getMatchDate()+"//"+
                    match.getFirstPlayerName()+"//"+
                    match.getSecondPlayerName()+"//"+
                    match.getWinnreName());
        }
    }

}
