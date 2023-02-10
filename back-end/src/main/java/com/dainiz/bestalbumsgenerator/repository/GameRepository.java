package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Game;
import com.dainiz.bestalbumsgenerator.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);

    @Query(
            value = "SELECT game FROM Game game WHERE game.user = ?1 and game.round = ?2"
    )
    List<Game> findByUserIdAndRound(User user, Integer round);

}