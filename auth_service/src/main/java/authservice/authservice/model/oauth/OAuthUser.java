package authservice.authservice.model.oauth;


import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthUser {

    private String id;
    private String name;
    private String email;
    private String imageUrl;
    private AddressStandardClaim address;
    private String birthdate;
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

}
