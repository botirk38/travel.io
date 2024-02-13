package authservice.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import authservice.authservice.model.jwt.User;
import authservice.authservice.model.oauth.OAuthUser;
import authservice.authservice.service.OAuthUserService;
import authservice.authservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OAuthUserService oauthUserService;

    @GetMapping("/me")
    public ResponseEntity<?> oauth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("User logged in: {} ", principal);

        if (principal instanceof OAuthUser) {
            OAuthUser user = (OAuthUser) principal;
            return ResponseEntity.ok(user);

        } else if (principal instanceof UserDetails) {

            UserDetails userDetails = (UserDetails) principal;

            return ResponseEntity.ok(userDetails);

        } else if (principal instanceof User) {
            User user = (User) principal;
            return ResponseEntity.ok(user);
        } else if (principal instanceof AnonymousAuthenticationToken) {

            User user = (User) principal;

            return ResponseEntity.ok(user);
        }

        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User type not recognized");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body("Username, password, name and email are required");
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

    @PostMapping("/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        // Fetch the authenticated user's username
        String authenticatedUsername = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            authenticatedUsername = ((UserDetails) principal).getUsername();
        } else {
            // Handle error or throw an exception as needed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        // Fetch the current user entity based on the authenticated username
        User currentUser = userService.findByUsername(authenticatedUsername);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Current user not found");
        }

        // Check if the ID matches the current user's ID
        if (!currentUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You're not authorized to update this profile");
        }

        if (updatedUser.getUsername() == null || updatedUser.getEmail() == null || updatedUser.getPhone() == null) {
            return ResponseEntity.badRequest().body("Username and email are required");
        }

        userService.updateUser(currentUser, updatedUser);

        return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));
    }

    @PostMapping("/update-oauth-profile")
    public ResponseEntity<?> updateOAuthProfile(@RequestBody OAuthUser updatedUser) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updatedUser.getUsername() == null || updatedUser.getEmail() == null || updatedUser.getName() == null
                || updatedUser.getBirthdate() == null || updatedUser.getPhoneNumber() == null) {
            return ResponseEntity.badRequest().body("Username, name and email are required");
        }

        if (principal instanceof OAuthUser) {
            OAuthUser currentUser = (OAuthUser) principal;
            // Validate and update the User
            oauthUserService.updateUser(currentUser, updatedUser);
        } else {
            return ResponseEntity.badRequest().body("Unknown user type");
        }

        return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request,
            HttpServletResponse response) {

        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),
                user.getPassword());
        Authentication authenticationResult = this.authenticationManager.authenticate(authenticationRequest);

        if (authenticationResult == null) {
            return ResponseEntity.badRequest().body("Login failed.");
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        logger.debug("User logged in: {} ", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest httpRequest = attrs.getRequest();
            HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                HttpSessionSecurityContextRepository repo = new HttpSessionSecurityContextRepository();
                repo.saveContext(SecurityContextHolder.getContext(), httpRequest, response);
            }
        }

        return ResponseEntity.ok(Map.of("message", "Login successful"));

    }

}
