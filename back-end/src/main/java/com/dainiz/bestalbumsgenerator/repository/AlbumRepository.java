package com.dainiz.bestalbumsgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dainiz.bestalbumsgenerator.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
