package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "firstPlayerId")
    private int firstPlayerId;

    @Column(name="secondPlayerId")
    private int secondPlayerId;

    @Column(name = "winner")
    private int winner;

    @Column(name = "date")
    public LocalDateTime date;

    public Match(){}

    public Match(int id, int firstPlayerId, int secondPlayerId, int winner, LocalDateTime date) {
        this.id = id;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.winner = winner;
        this.date = date;
    }
}
