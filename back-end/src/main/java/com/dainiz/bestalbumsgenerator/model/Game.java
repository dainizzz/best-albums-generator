package com.dainiz.bestalbumsgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Game {
    @SequenceGenerator(
            name = "game_id_sequence",
            sequenceName = "game_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_id_sequence"
    )
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    private Integer album1;
    private Integer album2;
    private Integer winner;

    public Game(Integer album1, Integer album2, Integer winner) {
        this.album1 = album1;
        this.album2 = album2;
        this.winner = winner;
    }

    public Game() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbum1() {
        return album1;
    }

    public void setAlbum1(int album1) {
        this.album1 = album1;
    }

    public int getAlbum2() {
        return album2;
    }

    public void setAlbum2(int album2) {
        this.album2 = album2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
