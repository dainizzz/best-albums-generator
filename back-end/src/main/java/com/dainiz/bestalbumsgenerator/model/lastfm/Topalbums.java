package com.dainiz.bestalbumsgenerator.model.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Topalbums (List<Album> album) {}