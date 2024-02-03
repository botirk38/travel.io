package authservice.authservice.service;

import authservice.authservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import authservice.authservice.model.User;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public String loginUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {

            String jws = Jwts.builder()
            .setSubject(foundUser.getUsername())
            .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
            .compact();
                    
            return jws;
        }
        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}
