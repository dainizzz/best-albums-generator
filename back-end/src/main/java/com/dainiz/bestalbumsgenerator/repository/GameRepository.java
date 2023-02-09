package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
