package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.Game;
import com.dainiz.bestalbumsgenerator.model.User;
import com.dainiz.bestalbumsgenerator.repository.AlbumRepository;
import com.dainiz.bestalbumsgenerator.repository.GameRepository;
import com.dainiz.bestalbumsgenerator.repository.UserRepository;
import com.dainiz.bestalbumsgenerator.service.GameService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users/{username}/games")
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    private final GameRepository gameRepository;
    public GameController(GameRepository gameRepository) {this.gameRepository = gameRepository;}
    @GetMapping
    public ResponseEntity<List<Game>> getAllGamesByUsername(@PathVariable("username") Integer username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException();
        }

        List<Game> games = gameRepository.findByUserId(username);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("{gameId}")
    public ResponseEntity<Game> getOneGameById(@PathVariable("gameId") Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(game);
    }

    @GetMapping("round/{roundNumber}")
    public ResponseEntity<List<Game>> getAllGamesForRound(@PathVariable("username") Integer username, @PathVariable("roundNumber") Integer roundNumber) {
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        List<Game> games = gameRepository.findByUserIdAndRound(user, roundNumber);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    record NewGameRequest(Integer album1, Integer album2, Integer winner, Integer round) {}

    @PostMapping
    public ResponseEntity<Game> addGame(@PathVariable("username") Integer username,
                                          @RequestBody GameController.NewGameRequest request) {
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        Game game = new Game();
        game.setAlbum1(request.album1());
        game.setAlbum2(request.album2());
        game.setWinner(request.winner());
        game.setRound(request.round());
        game.setUser(user);
        gameRepository.save(game);

        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PatchMapping({"{gameId}/{albumId}"})
    public ResponseEntity<Game> updateGame(
            @PathVariable("gameId") Integer gameId,
            @PathVariable("albumId") Integer albumId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(EntityNotFoundException::new);
        game.setWinner(albumId);
        gameRepository.save(game);

        return ResponseEntity.ok(game);
    }

    @DeleteMapping("{gameId}")
    public void deleteGame(@PathVariable("gameId") Integer gameId) { gameRepository.deleteById(gameId);}

    @DeleteMapping
    public ResponseEntity<List<Game>> deleteAllUserGames(@PathVariable("username") Integer username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException();
        }

        gameRepository.deleteByUserId(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    private GameService gameService;

    @PostMapping("round/{roundNumber}")
    public void saveGameRoundPairs(@PathVariable("roundNumber") Integer roundNumber, @PathVariable("username") Integer username) {
        // Create a new user
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        // Make call to album repository and get all albums by the id
        List<Album> albums = albumRepository.findByUserId(username);
        // Add the match pairings to user
        ArrayList<Game> gameRoundPairs = gameService.generateRoundPairings(albums, user, roundNumber);
        // Save to Game table
        gameRepository.saveAll(gameRoundPairs);
    }

}