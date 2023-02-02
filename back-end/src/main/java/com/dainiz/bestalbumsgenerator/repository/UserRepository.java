package com.dainiz.bestalbumsgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dainiz.bestalbumsgenerator.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    long deleteByName(String name);

}
