package authservice.authservice.service;

import authservice.authservice.model.jwt.User;
import authservice.authservice.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.Key;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);

        assertEquals("encodedPassword", result.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("username");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        User result = userService.findByUsername(user.getUsername());

        assertEquals(user, result);
    }

    @Test
    public void testValidateToken() {
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String validToken = Jwts.builder().setSubject("test").signWith(secretKey)
                .compact();
        String invalidToken = "invalidToken";

        assertDoesNotThrow(() -> userService.validateToken(validToken, secretKey));
        assertThrows(Exception.class, () -> userService.validateToken(invalidToken, secretKey));
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("email@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User result = userService.findByEmail(user.getEmail());

        assertEquals(user, result);
    }
}
