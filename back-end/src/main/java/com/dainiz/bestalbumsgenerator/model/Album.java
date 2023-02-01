package com.dainiz.bestalbumsgenerator.model;

import jakarta.persistence.*;

@Entity
//@Table(name = "albums")
public class Album {
    @Id
    private String mbid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
    private Integer playCount;
    private String imgLink;
    private String artist;
    private String releaseDate;
    private String userRank;

    public Album(String mbid, Integer playCount, String imgLink, String artist, String releaseDate, String userRank) {
        this.mbid = mbid;
        this.playCount = playCount;
        this.imgLink = imgLink;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.userRank = userRank;
    }

    public Album() {

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

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    // Create method in album
}