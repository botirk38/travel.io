package authservice.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import authservice.authservice.model.jwt.User;
import authservice.authservice.model.oauth.OAuthUser;
import authservice.authservice.repository.OAuthUserRepository;

@Service
public class OAuthUserService {

    @Autowired
    private OAuthUserRepository oAuthUserRepository;


    public void updateUser(OAuthUser currentUser, OAuthUser updatedUser) {

        boolean isModified = false;

        if (!currentUser.getName().equals(updatedUser.getName())) {
            currentUser.setName(updatedUser.getName());
            isModified = true;
        }

        if (!currentUser.getEmail().equals(updatedUser.getEmail())) {
            currentUser.setEmail(updatedUser.getEmail());
            isModified = true;
        }

        if (!currentUser.getBirthdate().equals(updatedUser.getBirthdate())) {
            currentUser.setBirthdate(updatedUser.getBirthdate());
            isModified = true;
        }

        if (!currentUser.getPhoneNumber().equals(updatedUser.getPhoneNumber())) {
            currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
            isModified = true;
        }

        if (isModified) {
            oAuthUserRepository.save(currentUser);
        }



    }

    


    
}
