package authservice.authservice.service;

import authservice.authservice.model.jwt.User;
import authservice.authservice.repository.UserRepository;


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

    public void updateUser(User currentUser, User updatedUser) {
        boolean isModified = false;
    
        if (!currentUser.getUsername().equals(updatedUser.getUsername())) {
            currentUser.setUsername(updatedUser.getUsername());
            isModified = true;
        }
    
        if (!currentUser.getEmail().equals(updatedUser.getEmail())) {
            currentUser.setEmail(updatedUser.getEmail());
            isModified = true;
        }
    
        if (!passwordEncoder.matches(updatedUser.getPassword(), currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            isModified = true;
        }

        if(!currentUser.getPhone().equals(updatedUser.getPhone())){
            currentUser.setPhone(updatedUser.getPhone());
            isModified = true;
        }

        if(!currentUser.getAddress().equals(updatedUser.getAddress())){
            currentUser.setAddress(updatedUser.getAddress());
            isModified = true;
        }

        if(!currentUser.getBirthdate().equals(updatedUser.getBirthdate())){
            currentUser.setBirthdate(updatedUser.getBirthdate());
            isModified = true;
        }

       
    
        if (isModified) {
            userRepository.save(currentUser);
        }
    }

    

}
