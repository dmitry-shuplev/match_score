package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchProcessDto {
    private String firstPlayerName;
    private String secondPlayerName;
    private String winnreName;
    private int firstPlayerGameScore;
    private int firstPlayerSetScore;
    private int firstPlayerMatchScore;
    private int secondPlayerGameScore;
    private int secondPlayerSetScore;
    private int secondPlayerMatchScore;

    public MatchProcessDto() {
        winnreName = "none";
        firstPlayerGameScore = 2;
        firstPlayerSetScore = 5;
        firstPlayerMatchScore = 1;
        secondPlayerGameScore = 0;
        secondPlayerSetScore = 0;
        secondPlayerMatchScore = 0;
    }
}
