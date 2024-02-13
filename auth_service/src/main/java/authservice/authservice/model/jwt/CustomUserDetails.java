package authservice.authservice.model.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;
    

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
        
    }

  

    public String getEmail() {
        return user.getEmail();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public String getDob() {
        return user.getBirthdate();
    }

    public Long getId() {
        return user.getId();
    }



   



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }



    @Override
    public String getUsername() {
        return user.getUsername();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }



    @Override
    public boolean isAccountNonLocked() {
        return true;
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



    @Override
    public boolean isEnabled() {
        return true;
    }
}