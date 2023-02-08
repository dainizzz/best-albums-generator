package com.dainiz.bestalbumsgenerator.model;

import jakarta.persistence.*;

@Entity
//@Table(name = "albums")
public class Album {
    @SequenceGenerator(
            name = "album_id_sequence",
            sequenceName = "album_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "album_id_sequence"
    )
    @Id
    private Integer id;

    private String mbid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
    private Integer playCount;
    private String imgLink;
    private String title;
    private String artist;
    private String releaseDate;
    private Integer userRank;

    public Album(String mbid, Integer playCount, String imgLink, String title, String artist, String releaseDate, Integer userRank) {
        this.mbid = mbid;
        this.playCount = playCount;
        this.imgLink = imgLink;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.userRank = userRank;
    }

    public Album() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public void rateAlbum() {
        this.userRank++;
    }
}