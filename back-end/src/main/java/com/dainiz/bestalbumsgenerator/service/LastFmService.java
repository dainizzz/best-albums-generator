package com.dainiz.bestalbumsgenerator.service;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.lastfm.*;
import com.dainiz.bestalbumsgenerator.model.musicbrainz.MusicbrainzData;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LastFmService {
    @Autowired
    private RestTemplate lastFmRestTemplate = new RestTemplate();

    private String getLfUrl(String username) {
        Dotenv dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&period=12month";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        return "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
    }

    public LastFmData getPojoLastFmData(String username) {
        String url = getLfUrl(username);
        return lastFmRestTemplate.getForObject(url, LastFmData.class);
    }

    public Object getRawLastFmData(String username) {
        String url = getLfUrl(username);
        return lastFmRestTemplate.getForObject(url, Object.class);
    }

    @Autowired
    private MusicbrainzService musicbrainzService = new MusicbrainzService();

    public ArrayList<Album> processUserAlbums(String username) {
        ArrayList<Album> topYearlyAlbums = new ArrayList<>();
        LastFmData lastFmData = getPojoLastFmData(username);
        Topalbums topalbums = lastFmData.getTopalbums();
        List<LfAlbum> lfAlbumList = topalbums.getLfAlbum();
        for (LfAlbum lfAlbum: lfAlbumList) {
            String mbid = lfAlbum.getMbid();
            if (mbid == null || mbid.isEmpty() || mbid.isBlank()) {
                continue;
            }
            try {
                // Can only make 1 request per second to musicbrainz
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MusicbrainzData mbDataDate = musicbrainzService.getPojoMusicbrainzData(mbid);
            String releaseDate = mbDataDate.getDate();
            if (releaseDate == null || releaseDate.isEmpty() || releaseDate.isBlank()) {
                continue;
            }
            String releaseYear = releaseDate.substring(0, 4);
            if (releaseYear.equals("2022")) {
                int playCountInt = Integer.parseInt(lfAlbum.getPlaycount());
                Artist artist = lfAlbum.getArtist();
                List<Image> images = lfAlbum.getImage();
                Image image = images.get(3);
                Album album = new Album(
                        mbid, playCountInt, image.getText(), lfAlbum.getName(), artist.name(), releaseDate, 0
                );
                topYearlyAlbums.add(album);
            }
            // Max num of albums = 16
            if (topYearlyAlbums.size() > 15) {
                break;
            }
        }
        // Ensuring album list has an even number of albums
        if (topYearlyAlbums.size() % 2 != 0){
            topYearlyAlbums.remove(topYearlyAlbums.size() - 1);
        }
        return topYearlyAlbums;
    }

}
