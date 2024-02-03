package authservice.authservice.controller;

import authservice.authservice.model.jwt.User;
import authservice.authservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("username", "password", "email@example.com");
    }

    @Test
    @WithMockUser(username = "testUser")
    @SuppressWarnings("null")
    public void testRegisterUser() throws Exception {
        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    @WithMockUser(username = "testUser")
    @SuppressWarnings("null")
    public void testGetUser() throws Exception {
        Mockito.when(userService.findByUsername("username")).thenReturn(user);

        mockMvc.perform(get("/users/username"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testLoginUser() throws Exception {
        String jws = "testJws";
        Mockito.when(userService.loginUser(Mockito.any(User.class))).thenReturn(jws);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(jws));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testLoginUserUnauthorized() throws Exception {
        Mockito.when(userService.loginUser(Mockito.any(User.class))).thenReturn(null);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testGetUserNotFound() throws Exception {
        Mockito.when(userService.findByUsername("username")).thenReturn(null);

        mockMvc.perform(get("/users/username"))
                .andExpect(status().isNotFound());
    }
}
