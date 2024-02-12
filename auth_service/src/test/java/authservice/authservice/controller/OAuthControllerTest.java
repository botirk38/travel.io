package authservice.authservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import authservice.authservice.model.oauth.OAuthUser;

class OAuthUserTest {

    @Test
    void fromGoogleUser_ValidDefaultOidcUser_ReturnsOAuthUser() {
        // Arrange
        DefaultOidcUser oidcUser = mock(DefaultOidcUser.class);
        when(oidcUser.getSubject()).thenReturn("123");
        when(oidcUser.getFullName()).thenReturn("John Doe");
        when(oidcUser.getEmail()).thenReturn("johndoe@example.com");
        when(oidcUser.getPicture()).thenReturn("http://example.com/johndoe.jpg");
        when(oidcUser.getAddress()).thenReturn(mock(AddressStandardClaim.class));
        when(oidcUser.getBirthdate()).thenReturn("1990-01-01");
        when(oidcUser.getPhoneNumber()).thenReturn("1234567890");

        // Act
        OAuthUser result = OAuthUser.fromGoogleUser(oidcUser);

        // Assert
        assertEquals("123", result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals("http://example.com/johndoe.jpg", result.getImageUrl());
        assertEquals("1990-01-01", result.getBirthdate());
        assertEquals("1234567890", result.getPhoneNumber());
    }

    @Test
    void fromOAuth2User_ValidOAuth2UserForGitHub_ReturnsOAuthUser() {
        // Arrange
        OAuth2User oAuth2User = mock(OAuth2User.class);
        when(oAuth2User.getName()).thenReturn("456");
        when(oAuth2User.getAttribute("name")).thenReturn("Jane Doe");
        when(oAuth2User.getAttribute("email")).thenReturn("janedoe@example.com");
        when(oAuth2User.getAttribute("avatar_url")).thenReturn("http://example.com/janedoe.jpg");

        // Act
        OAuthUser result = OAuthUser.fromOAuth2User(oAuth2User, "github");

        // Assert
        assertEquals("456", result.getId());
        assertEquals("Jane Doe", result.getName());
        assertEquals("janedoe@example.com", result.getEmail());
        assertEquals("http://example.com/janedoe.jpg", result.getImageUrl());
    }
}
