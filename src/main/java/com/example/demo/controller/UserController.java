package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUser(@RequestBody User user) {
        userRepository.save(user);

    }

    @RequestMapping(value = "/addUsers", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUser(@RequestBody List<User> users) {
        userRepository.saveAll(users);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    return userRepository.save(user);
                }).orElse(null);
    }
}
