package models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


    @Entity
    @Table(name = "test")
    @Getter
    @Setter
    public class Test {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Используем автоинкремент
        private int id;

        @Column(name = "name", nullable = false)
        private String name;

        public Test() {}

        public Test(String name) {
            this.name = name;
        }
    }

