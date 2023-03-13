package com.fika.rest.webservices.restfulwebservices.socialapplication.controller;

import com.fika.rest.webservices.restfulwebservices.socialapplication.exceptions.UserNotFoundException;
import com.fika.rest.webservices.restfulwebservices.socialapplication.models.Post;
import com.fika.rest.webservices.restfulwebservices.socialapplication.models.User;
import com.fika.rest.webservices.restfulwebservices.socialapplication.repository.UserRepository;
import com.fika.rest.webservices.restfulwebservices.socialapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/users")
public class UserControllerV2 {

    private UserService userService;
    private UserRepository userRepository;

    public UserControllerV2(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        return user.get();
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        var savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/getPosts/{id}")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        return user.get().getPosts();
    }

    // 41

}
