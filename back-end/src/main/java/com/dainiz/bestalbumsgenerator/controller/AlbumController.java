package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
@RequestMapping("api/v1/users/{username}/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;
    public AlbumController(AlbumRepository userRepo) {this.albumRepository = userRepo;}
    @GetMapping
    public List<Album> getAlbums() { return albumRepository.findAll();}

    record NewAlbumRequest(String mbid, Integer playCount, String imgLink, String artist, String releaseDate, String userRank){}

    @PostMapping
    public void addAlbum(@RequestBody NewAlbumRequest request) {
        Album album = new Album();
        album.setMbid(request.mbid());
        album.setArtist(request.artist());
        album.setImgLink(request.imgLink());
        album.setPlayCount(request.playCount());
        album.setUserRank(request.userRank());
        album.setReleaseDate(request.releaseDate());
        albumRepository.save(album);
    }

    @DeleteMapping("{albumId}")
    public void deleteUser(@PathVariable("albumId") String mbid) { albumRepository.deleteById(mbid);}


    // Create a method getAlbums(username) (similar to getLastFmUserAlbums)
        // Create variable for data e.g. Object data = LastFmService.getLastFmUserAlbums(username)
        // Create variable for albums from data.topalbums.album (this is the list of objects)
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

    // Add filtered albums to database


    // Move this to LastFmService
    // This would be getLastFmUserAlbumsRaw, and then a new method would manipulate the data
    @GetMapping("last-fm")
    public Object getLastFmUserAlbums(@PathVariable("username") String username) {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&from=1641024000&to=1672559999";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

    // Musicbrainz API Call (do same as LastFm)
    @GetMapping("{albumId}/{musicbrainz}")
    public Object getMusicBrainzData(@PathVariable("albumId") String mbid) {
        String url = "https://musicbrainz.org/ws/2/release/" + mbid + "?fmt=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }
}
