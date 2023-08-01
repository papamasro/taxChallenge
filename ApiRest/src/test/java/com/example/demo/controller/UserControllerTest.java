package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.exception.UsernameAlreadyExistException;
import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.services.user.UserRequest;
import com.example.demo.model.services.user.UserResponse;
import com.example.demo.service.impl.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Test
    void testCreateUserSuccess() {
        // Arrange
        UserRequest userRequest = new UserRequest("newUser", "password");
        UserEntity userEntity = new UserEntity(userRequest.getUser(), userRequest.getPassword());
        when(userService.existUsername(userEntity.getUsername())).thenReturn(false);
        when(userService.addUser(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        ResponseEntity<UserResponse> responseEntity = userController.createUser(userRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        UserResponse userResponse = responseEntity.getBody();
        assertNotNull(userResponse);
        assertEquals(userEntity.getUsername(), userResponse.getUser());
        assertEquals(userEntity.getId(), userResponse.getId());
    }

    @Test
    void testCreateUserUsernameAlreadyExists() {
        // Arrange
        UserRequest userRequest = new UserRequest("existingUser", "password");
        when(userService.existUsername(userRequest.getUser())).thenReturn(true);

        // Act & Assert
        assertThrows(UsernameAlreadyExistException.class, () -> userController.createUser(userRequest));
    }

    @Test
    void testLoginUserSuccess() {
        // Arrange
        UserRequest userRequest = new UserRequest("existingUser", "password");
        UserEntity userEntity = new UserEntity(userRequest.getUser(), userRequest.getPassword());
        when(userService.loadUserByUsername(userRequest.getUser())).thenReturn(userEntity);

        // Act
        ResponseEntity<UserResponse> responseEntity = userController.loginUser(userRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        UserResponse userResponse = responseEntity.getBody();
        assertNotNull(userResponse);
        assertEquals(userEntity.getUsername(), userResponse.getUser());
        assertEquals(userEntity.getId(), userResponse.getId());
    }

    @Test
    void testLoginUserUsernameNotFound() {
        // Arrange
        UserRequest userRequest = new UserRequest("nonExistingUser", "password");
        when(userService.loadUserByUsername(userRequest.getUser())).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userController.loginUser(userRequest));
    }
}