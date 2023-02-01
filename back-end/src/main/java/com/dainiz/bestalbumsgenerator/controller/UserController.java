package com.dainiz.bestalbumsgenerator.controller;

import com.dainiz.bestalbumsgenerator.model.User;
import com.dainiz.bestalbumsgenerator.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {this.userRepository = userRepository;}
    @GetMapping
    public List<User> getUsers() { return userRepository.findAll();}

    record NewUserRequest(String name){}

    @PostMapping
    public void addUser(@RequestBody NewUserRequest request) {
        User user = new User();
        user.setName(request.name());
        userRepository.save(user);
    }

    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable("username") String name) { userRepository.deleteByName(name);}
}
