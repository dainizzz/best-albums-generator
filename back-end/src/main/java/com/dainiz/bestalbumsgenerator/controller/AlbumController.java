package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.lastfm.LastFmData;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import com.dainiz.bestalbumsgenerator.service.LastFmService;
import org.springframework.beans.factory.annotation.Autowired;
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

    record NewAlbumRequest(String mbid, Integer playCount, String imgLink, String artist, String releaseDate, Integer userRank){}

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

    @Autowired
    private LastFmService lastFmService;

    @GetMapping("last-fm-pojo")
    public LastFmData getLastFmPojoData(@PathVariable("username") String username) {
        return lastFmService.getPojoLastFmData(username);
    }

    // Musicbrainz API Call TODO: A lot, but more or less do same as LastFm
    @GetMapping("{albumId}/{musicbrainz}")
    public Object getMusicBrainzData(@PathVariable("albumId") String mbid) {
        String url = "https://musicbrainz.org/ws/2/release/" + mbid + "?fmt=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }
}
