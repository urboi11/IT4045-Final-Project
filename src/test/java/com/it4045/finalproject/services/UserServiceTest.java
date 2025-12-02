package com.it4045.finalproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it4045.finalproject.dtos.LoginRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.exceptions.AccountDoesNotExistException;
import com.it4045.finalproject.mappers.UserAndCommentsMapper;
import com.it4045.finalproject.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Unit Tests")
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAndCommentsMapper userAndCommentsMapper;

    @InjectMocks
    private UserService userService;

    private User testUser;

    private UserDto testUserDto;

    @BeforeEach
    void setUp() {

        testUser = User.builder()
                .userid(1)
                .firstname("Jason")
                .lastname("Welsh")
                .email("jason.welsh@example.com")
                .password("OldPass123")
                .build();

        testUserDto = UserDto.builder()
                .userid(1)
                .firstname("Jason")
                .lastname("Welsh")
                .build();
    }

    @Nested
    @DisplayName("Login/Account Tests")
    class LoginAccountTests {
        
        @Test
        @DisplayName("Should login successfully with valid credentials")
        void login_WithValidCredentials_ShouldReturnUserDto() {

            when(userRepository.findByEmail("jason.welsh@example.com"))
                    .thenReturn(testUser);
            
            when(userAndCommentsMapper.UserDto(testUser))
                    .thenReturn(testUserDto);

            LoginRequest loginRequest = new LoginRequest(testUser.getEmail(), testUser.getPassword());
            UserDto result = userService.login(loginRequest);

            assertNotNull(result, "Result should not be null");
            assertEquals("jason.welsh@example.com", result.getFirstname());

            verify(userRepository, times(1)).findByEmail("jason.welsh@example.com");

            verify(userAndCommentsMapper, times(1)).UserDto(testUser);
        }
        
        @Test
        @DisplayName("Should throw AccountDoesNotExistException when email doesn't exist")
        void login_WithNonExistentEmail_ShouldThrowUserNotFoundException() {

            when(userRepository.findByEmail("nonexistent@example.com"))
                    .thenReturn(null);

            AccountDoesNotExistException exception = assertThrows(
                    AccountDoesNotExistException.class,
                    () -> userService.login(new LoginRequest("nonexistent@example", "somepassword"))
            );


            assertEquals("Invalid email or password", exception.getMessage());


            verify(userRepository, times(1)).findByEmail("nonexistent@example.com");

            verify(userAndCommentsMapper, never()).UserDto(any());
        }
    
    }
}
