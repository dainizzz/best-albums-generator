package com.dainiz.bestalbumsgenerator.model.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This is a key in the Album object
@JsonIgnoreProperties(ignoreUnknown = true)
public record Artist(String name) {}