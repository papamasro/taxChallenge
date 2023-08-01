package com.example.demo.service.impl.api;

import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements User {

    @Autowired
    LoggingEventService loggingEventService;

    @Autowired
    private UserRepository repo;

    Gson gson = new Gson();

    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username);
        if (user != null) {
            new UserEntity(user.getId(), user.getUsername());
            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("loadUserByUsername", 200, gson.toJson(user));
            });
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


    public Boolean existUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }


    public UserEntity addUser(UserEntity user) {
        UserEntity userRepo = repo.findByUsername(user.getUsername());

        if (userRepo == null) {
            UserEntity userSave = repo.save(new UserEntity(user.getUsername(), user.getPassword()));
            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("addUser", 200, gson.toJson(userSave)); //TODO REMOVE PASSWORD, OR MOVE LOGGING TO CONTROLLER
            });
            return userSave;
        } else {
            throw new UsernameNotFoundException("User Already exists");
        }
    }
}