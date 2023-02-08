package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.lastfm.LastFmData;
import com.dainiz.bestalbumsgenerator.model.musicbrainz.MusicbrainzData;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import com.dainiz.bestalbumsgenerator.service.LastFmService;
import com.dainiz.bestalbumsgenerator.service.MusicbrainzService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/users/{username}/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;
    public AlbumController(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}
    @GetMapping
    // TODO: Edit this to find all by username
    public List<Album> getAlbums() { return albumRepository.findAll();}

    @GetMapping("{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("albumId") Integer albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(album);
    }

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
    @PatchMapping({"albumId"})
    public ResponseEntity<Album> updateAlbum(@PathVariable("albumId") Integer albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(EntityNotFoundException::new);
        album.rateAlbum();
        albumRepository.save(album);

        return ResponseEntity.ok(album);
    }

    @DeleteMapping("{albumId}")
    public void deleteAlbum(@PathVariable("albumId") Integer albumId) { albumRepository.deleteById(albumId);}

    @DeleteMapping
    public void deleteAllAlbums() { albumRepository.deleteAll();}

    @Autowired
    private LastFmService lastFmService;

    @GetMapping("last-fm-pojo")
    public LastFmData getLastFmPojoData(@PathVariable("username") String username) {
        return lastFmService.getPojoLastFmData(username);
    }

    @GetMapping("last-fm-raw")
    public Object getLastFmRawData(@PathVariable("username") String username) {
        return lastFmService.getRawLastFmData(username);
    }

    @Autowired
    private MusicbrainzService musicbrainzService;

    @GetMapping("{albumId}/musicbrainz-pojo")
    public MusicbrainzData getMusicbrainzPojoData(HttpServletResponse response, @PathVariable("albumId") String mbid) {
        response.addHeader("Best Albums Generator/0.1", "dainiz.almazan@gmail.com");
        return musicbrainzService.getPojoMusicbrainzData(mbid);
    }

    @GetMapping("{albumId}/musicbrainz-raw")
    public Object getMusicBrainzRawData(@PathVariable("albumId") String mbid) {
        return musicbrainzService.getRawMusicbrainzData(mbid);
    }

    // In future updates, this will be updated to be a variable, so you can modify data from different years
    @PostMapping("2022")
    public void saveFilteredAlbums(@PathVariable("username") String username) {
        ArrayList<Album> filteredAlbums = lastFmService.processUserAlbums(username);
        albumRepository.saveAll(filteredAlbums);
    }
}
