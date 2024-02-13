package authservice.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import authservice.authservice.model.jwt.CustomUserDetails;
import authservice.authservice.model.jwt.User;
import authservice.authservice.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found.");
        }

        User user = optionalUser.get();
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        return new CustomUserDetails(user,
                authorities);
    }
}
