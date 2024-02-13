package authservice.authservice.model.oauth;


import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonProperty;

import authservice.authservice.model.IUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OAuthUser implements IUser {
    @Id
    private String id;
    @JsonProperty("username")
    private String name;
    private String email;
    private String imageUrl;
    @Transient
    private AddressStandardClaim address;
    private String birthdate;
    @JsonProperty("phone")
    private String phoneNumber;


    public static OAuthUser fromGoogleUser(DefaultOidcUser OAuthUser) {
        OAuthUser appUser = new OAuthUser();
        appUser.id = OAuthUser.getSubject();
        appUser.name = OAuthUser.getFullName();
        appUser.email = OAuthUser.getEmail();
        appUser.imageUrl = OAuthUser.getPicture();
        appUser.address = OAuthUser.getAddress();
        appUser.birthdate = OAuthUser.getBirthdate();
        appUser.phoneNumber = OAuthUser.getPhoneNumber();
        return appUser;
    }

    public static OAuthUser fromOAuth2User(OAuth2User oAuth2User, String provider) {
        OAuthUser appUser = new OAuthUser();
        appUser.id = oAuth2User.getName(); // or a specific attribute for user's unique ID
        appUser.name = oAuth2User.getAttribute("name"); 
        appUser.email = oAuth2User.getAttribute("email"); 

        // Handling for different providers if needed
        if ("github".equalsIgnoreCase(provider)) {
            appUser.imageUrl = oAuth2User.getAttribute("avatar_url"); // GitHub specific
        } else {
            // Default or other providers
            appUser.imageUrl = oAuth2User.getAttribute("picture"); 
        }

        return appUser;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    @Override
    public void setUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUsername'");
    }

}
