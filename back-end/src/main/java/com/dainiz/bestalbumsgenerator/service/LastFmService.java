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

    public LastFmData getPojoLastFmData(String username) {
        // TODO: Refactor this to avoid repetition of url
        Dotenv dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&period=12month";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
        return lastFmRestTemplate.getForObject(url, LastFmData.class);
    }

    public Object getRawLastFmData(String username) {
        Dotenv dotenv = Dotenv.configure().load();
        String params = "&format=json&limit=100&period=12month";
        String apiKey = dotenv.get("LAST_FM_TOKEN");
        String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + username + "&api_key=" + apiKey + params;
        return lastFmRestTemplate.getForObject(url, Object.class);
    }

    @Autowired
    private MusicbrainzService musicbrainzService = new MusicbrainzService();

    public ArrayList<Album> processUserAlbums(String username) {
        ArrayList<Album> topYearlyAlbums = new ArrayList<>();
        LastFmData lastFmData = getPojoLastFmData(username);
        Topalbums topalbums = lastFmData.getTopalbums();
        List<LfAlbum> lfAlbumList = topalbums.getLfAlbum();
        for (LfAlbum lfAlbum: lfAlbumList){
            String mbid = lfAlbum.getMbid();
            if (mbid == null || mbid.isEmpty() || mbid.isBlank()){
                continue;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MusicbrainzData mbDataDate = musicbrainzService.getPojoMusicbrainzData(mbid);
            String releaseDate = mbDataDate.getDate();
            if (releaseDate == null || releaseDate.isEmpty() || releaseDate.isBlank()){
                continue;
            }
            String releaseYear = releaseDate.substring(0,4);
            if (releaseYear.equals("2022")) {
                int playCountInt = Integer.parseInt(lfAlbum.getPlaycount());
                Artist artist = lfAlbum.getArtist();
                List<Image> images = lfAlbum.getImage();
                Image image = images.get(3);
                Album album = new Album(
                        mbid, playCountInt, image.getText(), lfAlbum.getName(), artist.name(),releaseDate, 0
                );
                topYearlyAlbums.add(album);
            }
            if (topYearlyAlbums.size() > 15) {
                 break;
            }
        }
        return topYearlyAlbums;
    }

//     TODO: Finish function - Add filtered albums (topYearlyAlbums) to database
//    public void addFilteredAlbums(String username, ArrayList<Album> filteredAlbums) {
//        String sql = "insert into user (first_name, age) values (?, ?)";
//
//        List<Object[]> userRows = new ArrayList<Object[]>();
//        for (User user : userList) {
//            userRows.add(new Object[] {user.getFirstName(), user.getAge()});
//        }
//
//        jdbcTemplate.batchUpdate(sql, userRows);
//    }


}
