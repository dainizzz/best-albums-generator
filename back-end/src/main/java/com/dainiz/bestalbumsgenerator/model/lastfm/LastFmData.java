package com.dainiz.bestalbumsgenerator.model.lastfm;

import java.util.Objects;

// This is the outermost layer of the last.fm json response;
public final class LastFmData {
    private final Topalbums topalbums;

    public Topalbums getTopalbums() {
        return topalbums;
    }

    public LastFmData(Topalbums topalbums) {
        this.topalbums = topalbums;
    }

    public Topalbums topalbums() {
        return topalbums;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LastFmData) obj;
        return Objects.equals(this.topalbums, that.topalbums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topalbums);
    }

    @Override
    public String toString() {
        return "LastFmData[" +
                "topalbums=" + topalbums + ']';
    }
}