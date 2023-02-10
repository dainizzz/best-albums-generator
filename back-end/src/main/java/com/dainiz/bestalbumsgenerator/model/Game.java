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

    // album1, album2, and winner hold the value of album ids
    private Integer album1;

    private Integer album2;
    private Integer winner;
    private Integer round;

    public Game(Integer album1, Integer album2, Integer winner, Integer round) {
        this.album1 = album1;
        this.album2 = album2;
        this.winner = winner;
        this.round = round;
    }

    public Game() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAlbum1() {
        return album1;
    }

    public void setAlbum1(Integer album1) {
        this.album1 = album1;
    }

    public Integer getAlbum2() {
        return album2;
    }

    public void setAlbum2(Integer album2) {
        this.album2 = album2;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
