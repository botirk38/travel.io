package authservice.authservice.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import authservice.authservice.oauth.Oauth2AuthenticationEntrypoint;
import authservice.authservice.oauth.Oauth2LoginSuccessHandler;
import authservice.authservice.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Value("${security.rememberMe.key}")
    private String rememberMeKey;

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // replace "*" with your frontend's
                                                                                 // origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); // this allows cookies to be sent cross-origin
        configuration.setExposedHeaders(Arrays.asList("Set-Cookie")); // this includes the Set-Cookie header in the
                                                                      // response
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RememberMeServices rememberMeServices() {
        RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                rememberMeKey, userDetailsService, encodingAlgorithm);
        rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
        rememberMeServices.setTokenValiditySeconds(1209600);
        rememberMeServices.setUseSecureCookie(false);

        return rememberMeServices;

    }

    @Bean
    RememberMeAuthenticationFilter rememberMeFilter() {
        RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(
                authenticationManager(), rememberMeServices());
        return rememberMeFilter;
    }

    @Bean
    SecurityFilterChain filterChainOAuth(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/login", "/user/register", "/oauth2/**").permitAll()
                        .anyRequest().authenticated())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .exceptionHandling(exceptionHandlingConfigurer -> {
                    exceptionHandlingConfigurer.authenticationEntryPoint(
                            new Oauth2AuthenticationEntrypoint());
                })
                .oauth2Login(customizer -> {
                    customizer
                            .successHandler(new Oauth2LoginSuccessHandler());
                });
        return http.build();
    }

}