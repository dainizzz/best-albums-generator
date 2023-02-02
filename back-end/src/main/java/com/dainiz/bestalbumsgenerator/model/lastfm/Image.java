package com.dainiz.bestalbumsgenerator.model.lastfm;

import com.fasterxml.jackson.annotation.JsonProperty;

// This is a key in the album object. It contains a list of objects.
public class Image {

    public String size;
    @JsonProperty("#text")
    public String text;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
