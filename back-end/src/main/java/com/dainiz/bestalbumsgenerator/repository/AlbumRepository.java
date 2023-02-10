package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Album;
import com.dainiz.bestalbumsgenerator.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);

//    @Query(
//            value = "SELECT album FROM Album album WHERE album.user = ?1 ORDER BY album.userRank"
//    )
    List<Album> findByUserIdOrderByUserRankDesc(Integer userId);
}
