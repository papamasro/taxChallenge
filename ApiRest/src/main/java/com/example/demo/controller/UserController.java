package com.example.demo.controller;

import com.example.demo.exception.UsernameAlreadyExistException;
import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.services.user.UserRequest;
import com.example.demo.model.services.user.UserResponse;
import com.example.demo.service.impl.api.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("createUser")
    @ResponseBody
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserEntity user = new UserEntity(userRequest.getUser(), userRequest.getPassword());
        if (Boolean.FALSE.equals(userService.existUsername(user.getUsername()))) {
            UserEntity userEntity = userService.addUser(user);
            UserResponse userResponse = new UserResponse(userEntity.getUsername(), userEntity.getId());
            return ResponseEntity.ok(userResponse);
        } else
            throw new UsernameAlreadyExistException("user already exists");
    }

    @GetMapping("login")
    @ResponseBody
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody UserRequest userRequest) {
        UserEntity userEntity = userService.loadUserByUsername(userRequest.getUser());
        if (userEntity != null) {
            UserResponse userResponse = new UserResponse(userEntity.getUsername(), userEntity.getId());
            return ResponseEntity.ok(userResponse);
        } else
            throw new UsernameNotFoundException("user not registered");
    }

}
