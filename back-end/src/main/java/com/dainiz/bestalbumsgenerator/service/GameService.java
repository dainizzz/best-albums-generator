package com.dainiz.bestalbumsgenerator.service;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.Game;
import com.dainiz.bestalbumsgenerator.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public ArrayList<Game> generateRoundPairings(List<Album> albumsList, User user, Integer round) {
        ArrayList<Album> noWins = new ArrayList<>();
        ArrayList<Album> oneWin = new ArrayList<>();
        ArrayList<Album> twoWins = new ArrayList<>();
        ArrayList<Album> threeWins = new ArrayList<>();

        for (Album album: albumsList){
            int currentWins = album.getUserRank();
            if (currentWins == 0){
                noWins.add(album);
            } else if (currentWins == 1) {
                oneWin.add(album);
            } else if (currentWins == 2) {
                twoWins.add(album);
            } else {
                threeWins.add(album);
            }
        }

        ArrayList<Game> noWinParings = makePairings(noWins, user, round);
        ArrayList<Game> oneWinPairings = makePairings(oneWin, user, round);
        ArrayList<Game> twoWinsPairings = makePairings(twoWins, user, round);
        ArrayList<Game> threeWinsPairings = makePairings(threeWins, user, round);

        ArrayList<Game> roundPairings = new ArrayList<>();
        roundPairings.addAll(noWinParings);
        roundPairings.addAll(oneWinPairings);
        roundPairings.addAll(twoWinsPairings);
        roundPairings.addAll(threeWinsPairings);

        return roundPairings;

    }

    private ArrayList<Game> makePairings(ArrayList<Album> weightClass, User user, Integer round) {
        ArrayList<Game> weightClassPairings = new ArrayList<>();
        if (weightClass.size() != 0) {
            int midpoint = weightClass.size() / 2;
            int i = 0;
            int j = midpoint;
            while (i < midpoint) {
                Game game = new Game();
                game.setAlbum1(weightClass.get(i).getId());
                game.setAlbum2(weightClass.get(j).getId());
                game.setUser(user);
                game.setRound(round);
                game.setWinner(0);
                weightClassPairings.add(game);
                i++;
                j++;
            }
        }
        return weightClassPairings;
    }

}
