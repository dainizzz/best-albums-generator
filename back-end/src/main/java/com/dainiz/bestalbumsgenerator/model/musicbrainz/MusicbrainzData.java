package com.dainiz.bestalbumsgenerator.model.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicbrainzData {

    public String date;

    public MusicbrainzData(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
