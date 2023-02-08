package com.dainiz.bestalbumsgenerator.model;

import jakarta.persistence.*;

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
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
    private int album1;
    private int album2;
    private int winner;

    public Game(int album1, int album2, int winner) {
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
