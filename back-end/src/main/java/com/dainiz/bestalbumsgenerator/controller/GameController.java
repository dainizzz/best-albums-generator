package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.Game;
import com.dainiz.bestalbumsgenerator.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{username}/game")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {this.gameRepository = gameRepository;}
    @GetMapping
    public List<Game> getGames() { return gameRepository.findAll();}

    @GetMapping("{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable("gameId") Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(game);
    }

    record NewGameRequest(Integer album1, Integer album2, Integer winner){}

    @PostMapping
    public void addGame(@RequestBody NewGameRequest request) {
        Game game = new Game();
        game.setAlbum1(request.album1());
        game.setAlbum2(request.album2());
        game.setWinner(request.winner());
        gameRepository.save(game);
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
    public void deleteGame(@PathVariable("gameId") Integer gameId) {
        gameRepository.deleteById(gameId);
    }

    @DeleteMapping
    public void deleteAllAlbums() { gameRepository.deleteAll(); }
}
