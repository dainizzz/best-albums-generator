package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.User;
import com.dainiz.bestalbumsgenerator.model.lastfm.LastFmData;
import com.dainiz.bestalbumsgenerator.model.musicbrainz.MusicbrainzData;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import com.dainiz.bestalbumsgenerator.repository.UserRepository;
import com.dainiz.bestalbumsgenerator.service.LastFmService;
import com.dainiz.bestalbumsgenerator.service.MusicbrainzService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/users/{username}/albums")
public class AlbumController {

    @Autowired
    private UserRepository userRepository;

    private final AlbumRepository albumRepository;
    public AlbumController(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbumsByUsername(@PathVariable(value = "username") Integer username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException();
        }

        List<Album> albums = albumRepository.findByUserId(username);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("albumId") Integer albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(album);
    }

    @GetMapping("ranked")
    public ResponseEntity<List<Album>> getAllAlbumsByUsernameRanked(@PathVariable(value = "username") Integer username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException();
        }

        List<Album> albums = albumRepository.findByUserIdOrderByUserRankDesc(username);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    record NewAlbumRequest(String mbid, Integer playCount, String imgLink, String artist, String releaseDate, Integer userRank){}

    @PostMapping
    public ResponseEntity<Album> addAlbum(@PathVariable("username") Integer username,
                                          @RequestBody NewAlbumRequest request) {
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        Album album = new Album();
        album.setMbid(request.mbid());
        album.setArtist(request.artist());
        album.setImgLink(request.imgLink());
        album.setPlayCount(request.playCount());
        album.setUserRank(request.userRank());
        album.setReleaseDate(request.releaseDate());
        album.setUser(user);
        albumRepository.save(album);

        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }

    @PatchMapping({"{albumId}"})
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
    public ResponseEntity<List<Album>> deleteAllUserAlbums(@PathVariable("username") Integer username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException();
        }

        albumRepository.deleteByUserId(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


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
    public void saveFilteredAlbums(@PathVariable("username") Integer username) {
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        ArrayList<Album> filteredAlbums = lastFmService.processUserAlbums(user);
        albumRepository.saveAll(filteredAlbums);
    }
}
