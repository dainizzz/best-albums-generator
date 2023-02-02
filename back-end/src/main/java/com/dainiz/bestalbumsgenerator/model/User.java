package com.dainiz.bestalbumsgenerator.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Album> albums;


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
}