package com.dainiz.bestalbumsgenerator.repository;

import com.dainiz.bestalbumsgenerator.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AlbumRepository extends JpaRepository<Album, String> {
}
