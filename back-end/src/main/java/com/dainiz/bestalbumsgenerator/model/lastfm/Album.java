package com.dainiz.bestalbumsgenerator.model.lastfm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// One of the key values ("album") for LastFmData; this is a list of albums
public class Album {

    public Artist artist;
    public List<Image> image;
    public String mbid;
    public String url;
    public String playcount;
    @JsonProperty("@attr")
    public Attr attr;
    public String name;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
