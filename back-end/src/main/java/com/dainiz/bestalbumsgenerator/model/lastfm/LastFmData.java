package com.dainiz.bestalbumsgenerator.model.lastfm;


// This is the outermost layer of the last.fm json response;
public class LastFmData {
    private Topalbums topalbums;

    public LastFmData(Topalbums topalbums) {
        this.topalbums = topalbums;
    }

    public Topalbums getTopalbums() {
        return topalbums;
    }

    private LastFmData() {}

    public void setTopalbums(Topalbums topalbums) {
        this.topalbums = topalbums;
    }


}