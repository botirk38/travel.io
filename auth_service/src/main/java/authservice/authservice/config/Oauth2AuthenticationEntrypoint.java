package authservice.authservice.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.ServletException;

@Slf4j
public class Oauth2AuthenticationEntrypoint implements AuthenticationEntryPoint {

    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.debug("Oauth2AuthenticationEntrypoint.commence, authException: {}", authException.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().println("{ \"error\": \"" + authException.getMessage() + "\" }");
        response.getWriter().flush();
    }

}
