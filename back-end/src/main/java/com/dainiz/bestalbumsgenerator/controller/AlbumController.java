package com.dainiz.bestalbumsgenerator.controller;


import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/albums")
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
}
