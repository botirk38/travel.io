package authservice.authservice.model.oauth;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

@Getter
public class OAuthUser {

    private String id;
    private String name;
    private String email;
    private String imageUrl;

    public static OAuthUser fromGoogleUser(DefaultOidcUser OAuthUser) {
        OAuthUser appUser = new OAuthUser();
        appUser.id = OAuthUser.getSubject();
        appUser.name = OAuthUser.getFullName();
        appUser.email = OAuthUser.getEmail();
        appUser.imageUrl = OAuthUser.getPicture();
        return appUser;
    }

    public static OAuthUser fromOAuth2User(OAuth2User oAuth2User, String provider) {
        OAuthUser appUser = new OAuthUser();
        // You may need to adjust the attribute names based on your OAuth2 provider
        appUser.id = oAuth2User.getName(); // or a specific attribute for user's unique ID
        appUser.name = oAuth2User.getAttribute("name"); // Adjust based on the provider's response
        appUser.email = oAuth2User.getAttribute("email"); // Adjust based on the provider's response

        // Handling for different providers if needed
        if ("github".equalsIgnoreCase(provider)) {
            appUser.imageUrl = oAuth2User.getAttribute("avatar_url"); // GitHub specific
        } else {
            // Default or other providers
            appUser.imageUrl = oAuth2User.getAttribute("picture"); // Adjust based on the provider's response
        }

        return appUser;
    }

}
