package authservice.authservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import authservice.authservice.model.oauth.OAuthUser;

public interface OAuthUserRepository extends JpaRepository<OAuthUser, String>{

    Optional<OAuthUser> findByName(String name);

    Optional<OAuthUser> findByEmail(String email);

    
    
}
