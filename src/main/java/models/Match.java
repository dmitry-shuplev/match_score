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

    @Column(name = "date")
    private LocalDateTime date;

    public Match() {}

    public Match(int id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }
}