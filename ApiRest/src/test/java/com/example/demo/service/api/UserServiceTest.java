package com.example.demo.service.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testLoadUserByUsernameSuccess() {
        // Arrange
        String username = "existingUser";
        UserEntity userEntity = new UserEntity(1L, username);
        when(userRepository.findByUsername(username)).thenReturn(userEntity);

        // Act
        UserEntity resultUser = userService.loadUserByUsername(username);

        // Assert
        assertNotNull(resultUser);
        assertEquals(userEntity.getId(), resultUser.getId());
        assertEquals(userEntity.getUsername(), resultUser.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        // Arrange
        String username = "nonExistingUser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }

    @Test
    void testExistUsernameUserExists() {
        // Arrange
        String username = "existingUser";
        when(userRepository.findByUsername(username)).thenReturn(new UserEntity(1L, username));

        // Act
        boolean result = userService.existUsername(username);

        // Assert
        assertTrue(result);
    }

    @Test
    void testExistUsernameUserNotExists() {
        // Arrange
        String username = "nonExistingUser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act
        boolean result = userService.existUsername(username);

        // Assert
        assertFalse(result);
    }

    @Test
    void testAddUserSuccess() {
        // Arrange
        String username = "newUser";
        String password = "password";
        UserEntity userToAdd = new UserEntity(username, password);
        when(userRepository.findByUsername(username)).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity(1L, username, password));

        // Act
        UserEntity resultUser = userService.addUser(userToAdd);

        // Assert
        assertNotNull(resultUser);
        assertEquals(username, resultUser.getUsername());
        assertEquals(password, resultUser.getPassword());
    }

    @Test
    void testAddUserUsernameAlreadyExists() {
        // Arrange
        String username = "existingUser";
        UserEntity userToAdd = new UserEntity(username, "password");
        when(userRepository.findByUsername(username)).thenReturn(new UserEntity(1L, username));

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.addUser(userToAdd));
    }
}