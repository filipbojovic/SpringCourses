package com.fika.rest.webservices.restfulwebservices.socialapplication.service;

import com.fika.rest.webservices.restfulwebservices.socialapplication.models.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Filip", Instant.now()));
        users.add(new User(++usersCount, "Anita", Instant.now()));
        users.add(new User(++usersCount, "Ana", Instant.now()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findUser(Integer id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
