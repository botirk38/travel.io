package authservice.authservice.service;

import authservice.authservice.model.jwt.User;
import authservice.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
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

   
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public User updateUser(User currentUser, User updatedUser) {
        boolean isModified = false;

        if (currentUser == null || updatedUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    
        // Check and update password
        if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            isModified = true; // Assuming you want to mark as modified if password changes
        }
        
        // Username
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(currentUser.getUsername())) {
            currentUser.setUsername(updatedUser.getUsername());
            isModified = true;
        }
        
        // Email
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(currentUser.getEmail())) {
            currentUser.setEmail(updatedUser.getEmail());
            isModified = true;
        }
    
        // Phone
        if (updatedUser.getPhone() != null && !updatedUser.getPhone().equals(currentUser.getPhone())) {
            currentUser.setPhone(updatedUser.getPhone());
            isModified = true;
        }
    
        // Address
        if (updatedUser.getAddress() != null && !updatedUser.getAddress().equals(currentUser.getAddress())) {
            currentUser.setAddress(updatedUser.getAddress());
            isModified = true;
        }
    
        // Birthdate
        if (updatedUser.getBirthdate() != null && !updatedUser.getBirthdate().equals(currentUser.getBirthdate())) {
            currentUser.setBirthdate(updatedUser.getBirthdate());
            isModified = true;
        }
    
        // Save changes if any field was modified
        if (isModified) {

            userRepository.save(currentUser);
        }

        return currentUser;
    }
    

    

}
