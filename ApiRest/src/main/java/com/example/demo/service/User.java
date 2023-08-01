package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;

public interface User {

    Boolean existUsername(String username);

     UserEntity loadUserByUsername(String username);

    UserEntity addUser(UserEntity user);
}
