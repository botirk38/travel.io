package authservice.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import authservice.authservice.model.jwt.User;
import authservice.authservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RememberMeServices remememberMeServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body("Username, password and email are required");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        else {
            userService.registerUser(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request,
            HttpServletResponse response) {

        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),
                user.getPassword());
        Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);

        if (authenticationResult == null) {
            return ResponseEntity.badRequest().body("Login failed.");
        }

        remememberMeServices.loginSuccess(request, response, authenticationResult);

        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        return ResponseEntity.ok(Map.of("message", "Login successful"));

    }

}
