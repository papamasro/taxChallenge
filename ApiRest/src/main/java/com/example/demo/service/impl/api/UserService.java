package com.example.demo.service.impl.api;

import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements User {

    @Autowired
    private UserRepository repo;

    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username);
        if (user != null) {
            return new UserEntity(user.getId(), user.getUsername());
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
            return repo.save(new UserEntity(user.getUsername(), user.getPassword()));
        } else {
            throw new UsernameNotFoundException("User Already exists");
        }
    }
}