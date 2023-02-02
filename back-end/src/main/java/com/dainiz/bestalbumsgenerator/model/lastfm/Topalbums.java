package com.dainiz.bestalbumsgenerator.model.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Topalbums {
    @JsonProperty("album")
    public List<LfAlbum> lfAlbum;

    public Topalbums(List<LfAlbum> lfAlbum) {
        this.lfAlbum = lfAlbum;
    }

    public List<LfAlbum> getLfAlbum() {
        return lfAlbum;
    }

    public void setLfAlbum(List<LfAlbum> lfAlbum) {
        this.lfAlbum = lfAlbum;
    }
}