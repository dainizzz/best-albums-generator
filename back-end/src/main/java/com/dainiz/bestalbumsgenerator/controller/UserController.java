package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.User;
import com.dainiz.bestalbumsgenerator.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {this.userRepository = userRepository;}
    @GetMapping
    public List<User> getUsers() { return userRepository.findAll();}

    @GetMapping({"{username}"})
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userRepository.findByName(username);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    record NewUserRequest(String name){}

    @PostMapping
    public void addUser(@RequestBody NewUserRequest request) {
        User user = new User();
        user.setName(request.name());
        userRepository.save(user);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") int id) { userRepository.deleteById(id);}
}
