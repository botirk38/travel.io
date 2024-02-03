package authservice.authservice.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import authservice.authservice.model.oauth.OAuthUser;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/me")
    public ResponseEntity<OAuthUser>  oauth() {
        OAuthUser user = (OAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

}
