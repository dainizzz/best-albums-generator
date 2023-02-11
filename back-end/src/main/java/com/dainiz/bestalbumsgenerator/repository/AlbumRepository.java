package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);

    List<Album> findTop10ByUserIdOrderByUserRankDesc(Integer userId);
}
