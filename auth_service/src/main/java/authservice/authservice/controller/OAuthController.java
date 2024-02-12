package authservice.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import authservice.authservice.model.jwt.User;
import authservice.authservice.model.oauth.OAuthUser;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/me")
    public ResponseEntity<?>  oauth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof OAuthUser) {
            OAuthUser user = (OAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(user);
           
        } else if (principal instanceof User) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User type not recognized");
        }

    }

}
