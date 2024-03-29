package authservice.authservice.oauth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import authservice.authservice.model.oauth.OAuthAuthenticationToken;
import authservice.authservice.model.oauth.OAuthUser;
import authservice.authservice.repository.OAuthUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private OAuthUserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("Authentication success: {}", authentication);

        OAuthUser appUser = null;

        if (authentication.getPrincipal() instanceof DefaultOidcUser) {
            DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
            appUser = OAuthUser.fromGoogleUser(oidcUser);
        } else if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();
            appUser = OAuthUser.fromOAuth2User(oauth2User, "oauth2");
        }

        if (appUser != null) {
            userRepository.save(appUser);
            OAuthAuthenticationToken token = new OAuthAuthenticationToken(appUser);
            SecurityContextHolder.getContext().setAuthentication(token);
            response.sendRedirect("http://localhost:3000/home");
        } else {
            throw new IllegalStateException(
                    "Unsupported authentication type: " + authentication.getPrincipal().getClass());
        }
    }
}
