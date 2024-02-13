package authservice.authservice.service;

import authservice.authservice.model.jwt.User;
import authservice.authservice.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("email@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User result = userService.findByEmail(user.getEmail());

        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User currentUser = new User();
        currentUser.setUsername("oldUsername");
        currentUser.setEmail("oldEmail@example.com");
        currentUser.setPassword(passwordEncoder.encode("oldPassword"));
        currentUser.setPhone("oldPhone");
        currentUser.setAddress("oldAddress");
        currentUser.setBirthdate("1990/01/01");

        User updatedUser = new User();
        updatedUser.setUsername("newUsername");
        updatedUser.setEmail("newEmail@example.com");
        updatedUser.setPassword("newPassword");
        updatedUser.setPhone("newPhone");
        updatedUser.setAddress("newAddress");
        updatedUser.setBirthdate("2000/01/01");

        when(passwordEncoder.matches(updatedUser.getPassword(), currentUser.getPassword())).thenReturn(false);
        when(passwordEncoder.encode(updatedUser.getPassword())).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.updateUser(currentUser, updatedUser);

        assertEquals(updatedUser.getUsername(), currentUser.getUsername());
        assertEquals(updatedUser.getEmail(), currentUser.getEmail());
        assertEquals("encodedNewPassword", currentUser.getPassword());
        assertEquals(updatedUser.getPhone(), currentUser.getPhone());
        assertEquals(updatedUser.getAddress(), currentUser.getAddress());
        assertEquals(updatedUser.getBirthdate(), currentUser.getBirthdate());

        verify(userRepository, times(1)).save(currentUser);
    }

    @Test
    public void testUpdateUserNoChanges() {
        User currentUser = new User();

        currentUser.setId(1L);
        currentUser.setUsername("username");
        currentUser.setEmail("email");
        currentUser.setPassword(passwordEncoder.encode("password"));
        currentUser.setPhone("phone");
        currentUser.setAddress("address");
        currentUser.setBirthdate("1990/01/01");

        User updatedUser = new User();
        currentUser.setId(1L);
        updatedUser.setUsername("username");
        updatedUser.setEmail("email");
        updatedUser.setPassword("password");
        updatedUser.setPhone("phone");
        updatedUser.setAddress("address");
        updatedUser.setBirthdate("1990/01/01");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(currentUser));


        userService.updateUser(currentUser, updatedUser);

        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    public void testUpdateUserNoChangesWithNullFields() {
        User currentUser = new User();

        currentUser.setId(1L);
        currentUser.setUsername("username");
        currentUser.setEmail("email");
        currentUser.setPassword(passwordEncoder.encode("password"));
        currentUser.setPhone("phone");
        currentUser.setAddress("address");
        currentUser.setBirthdate("1990/01/01");

        User updatedUser = new User();
        currentUser.setId(1L);
        updatedUser.setUsername(null);
        updatedUser.setEmail(null);
        updatedUser.setPassword(null);
        updatedUser.setPhone(null);
        updatedUser.setAddress(null);
        updatedUser.setBirthdate(null);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(currentUser));

}

    @Test
    public void testUpdateUserWithNullFields() {
        User currentUser = new User();

        currentUser.setId(1L);
        currentUser.setUsername("username");
        currentUser.setEmail("email");
        currentUser.setPassword(passwordEncoder.encode("password"));
        currentUser.setPhone("phone");
        currentUser.setAddress("address");
        currentUser.setBirthdate("1990/01/01");

        User updatedUser = new User();
        currentUser.setId(1L);
        updatedUser.setUsername(null);
        updatedUser.setEmail(null);
        updatedUser.setPassword(null);
        updatedUser.setPhone(null);
        updatedUser.setAddress(null);
        updatedUser.setBirthdate(null);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(currentUser));

        userService.updateUser(currentUser, updatedUser);

        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    public void testUpdateUserWithOneChange() {
        User currentUser = new User();

        currentUser.setId(1L);
        currentUser.setUsername("username");
        currentUser.setEmail("email");
        currentUser.setPassword(passwordEncoder.encode("password"));
        currentUser.setPhone("phone");
        currentUser.setAddress("address");
        currentUser.setBirthdate("1990/01/01");

        User updatedUser = new User();
        currentUser.setId(1L);
        updatedUser.setUsername("newUsername");
        updatedUser.setEmail("email");
        updatedUser.setPassword("password");
        updatedUser.setPhone("phone");
        updatedUser.setAddress("address");
        updatedUser.setBirthdate("1990/01/01");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(currentUser));

        userService.updateUser(currentUser, updatedUser);

        verify(userRepository, times(1)).save(any(User.class));
    }

}