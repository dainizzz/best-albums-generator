package com.dainiz.bestalbumsgenerator.service;

import com.dainiz.bestalbumsgenerator.model.lastfm.LastFmData;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@RestController
public class LastFmService {
    @Autowired
    private RestTemplate lastFmRestTemplate = new RestTemplate();

    public LastFmData getPojoLastFmData(String username) {
        Dotenv dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&from=1641024000&to=1672559999";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
        return lastFmRestTemplate.getForObject(url, LastFmData.class);
    }

    public Object getLastFmAlbumsRaw(String username) {
        Dotenv dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&from=1641024000&to=1672559999";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
        return lastFmRestTemplate.getForObject(url, Object.class);
    }

    // TODO: Finish this function
//    public static ArrayList<Album> processUserAlbums(String username) {
//        Object data = getLastFmAlbumsRaw(username);

//        Object[] albums = data.topalbums.album;
        // Create empty list
        // Iterate through each album object in albums
        // Create object with following:
        //    Mbid = album.mbid
        //    Playcount = album.playcount
        //    ImgLink = album.image.[4].#text
        //    Url = album.url
        //    Artist = album.artist.name
        // Add object to empty list

        // Go through list of albums and make MB api call, aysnc
    // }

    // Add filtered albums to database



}
