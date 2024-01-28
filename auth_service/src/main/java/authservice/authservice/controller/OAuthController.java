package authservice.authservice.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String githubClientId;

    @Value("${spring.security.oauth2.client.registration.github.redirect-uri}")
    private String githubRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal Object principal) {
        if (principal instanceof OAuth2User) {
            return Collections.singletonMap("name", ((OAuth2User) principal).getAttribute("name"));
        } else if (principal instanceof UserDetails) {
            return Collections.singletonMap("name", ((UserDetails) principal).getUsername());
        }
        throw new IllegalStateException("Unexpected principal type");
    }

    

}
