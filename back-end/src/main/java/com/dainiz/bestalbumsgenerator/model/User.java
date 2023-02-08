package com.dainiz.bestalbumsgenerator.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
//@Table(name = "users")
public class User {
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Id
    private int id;

    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Album> albums;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Game> games;


    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}