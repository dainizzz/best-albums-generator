package com.dainiz.bestalbumsgenerator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicbrainzService {
    // TODO: Create POJO for Musicbrainz data & process it
    public Object getMusicBrainzDataRaw(String mbid) {
        String url = "https://musicbrainz.org/ws/2/release/" + mbid + "?fmt=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

}
