package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlbumRepository extends JpaRepository<Album, String> {
//    List<Album> findByUserId(Integer userId);
//
//    @Transactional
//    void deleteAlbumsBy(int userId);
}
