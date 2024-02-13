package authservice.authservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import authservice.authservice.model.jwt.User;
import authservice.authservice.service.UserService;

public class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

  

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito annotations
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @SuppressWarnings("null")
    @Test
    public void registerUser_Success() throws Exception {
        User user = new User("testUser", "password", "test@example.com");

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"));

        verify(userService, times(1)).registerUser(any(User.class));
    }

    @SuppressWarnings("null")
    @Test
    public void registerUser_Fail_UsernameExists() throws Exception {
        User user = new User("testUser", "password", "test@example.com");

        when(userService.findByUsername(user.getUsername())).thenReturn(new User());

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));

        verify(userService, times(1)).findByUsername(anyString());
    }

    @SuppressWarnings("null")
    @Test
    public void loginUser_Success() throws Exception {
        User user = new User("testUser", "password", null);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"));

        verify(authenticationManager, times(1)).authenticate(any(Authentication.class));
    }

}
