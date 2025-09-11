package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchWebDto {

        private String matchDate;
        private String firstPlayerName;
        private String secondPlayerName;
        private String winnreName;

    public MatchWebDto(String matchDate, String firstPlayerName, String secondPlayerName, String winnreName) {
        this.matchDate = matchDate;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.winnreName = winnreName;
    }
}


