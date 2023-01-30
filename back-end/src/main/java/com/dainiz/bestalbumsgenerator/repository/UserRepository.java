package com.dainiz.bestalbumsgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.dainiz.bestalbumsgenerator.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
//    List<User> findByName(String name);
}
