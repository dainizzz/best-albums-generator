package com.dainiz.bestalbumsgenerator.service;

import com.dainiz.bestalbumsgenerator.model.musicbrainz.MusicbrainzData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MusicbrainzService {
    @Autowired

    private RestTemplate musicbrainzRestTemplate = new RestTemplate();

    public MusicbrainzData getPojoMusicbrainzData(String mbid) {
        String url = "https://musicbrainz.org/ws/2/release/" + mbid + "?fmt=json";
        return musicbrainzRestTemplate.getForObject(url, MusicbrainzData.class);
    }

    public Object getRawMusicbrainzData(String mbid) {
        String url = "https://musicbrainz.org/ws/2/release/" + mbid + "?fmt=json";
        return musicbrainzRestTemplate.getForObject(url, Object.class);
    }
}
