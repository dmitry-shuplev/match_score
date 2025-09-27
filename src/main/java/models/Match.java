package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "firstplayerid")
    private int firstPlayerId;

    @Column(name = "secondplayerid")
    private int secondPlayerId;

    @Column(name = "winner")
    private int winner;

    public Match() {
    }

    public Match(int id, LocalDateTime date, int firstPlayerId, int secondPlayerId, int winner) {
        this.id = id;
        this.date = date;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("HH:mm|dd.MM"))
                + " || " + firstPlayerId
                + " || " + secondPlayerId
                + " || " + winner
                + "\n";
    }
}