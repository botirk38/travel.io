package authservice.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import authservice.authservice.jwt.JwtAuthenticationFilter;
import authservice.authservice.oauth.Oauth2AuthenticationEntrypoint;
import authservice.authservice.oauth.Oauth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class OAuthSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public OAuthSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChainOAuth(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/login", "/user/register", "/oauth2/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandlingConfigurer -> {
                    exceptionHandlingConfigurer.authenticationEntryPoint(
                            new Oauth2AuthenticationEntrypoint());
                })
                .oauth2Login(customizer -> {
                    customizer
                            .successHandler(new Oauth2LoginSuccessHandler());
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}