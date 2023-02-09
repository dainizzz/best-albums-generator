package com.dainiz.bestalbumsgenerator.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dainiz.bestalbumsgenerator.model.Album;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByUserId(Integer userId);

    @Transactional
    void deleteByUserId(Integer userId);
}
