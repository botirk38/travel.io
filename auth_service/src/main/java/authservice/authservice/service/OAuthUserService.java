package authservice.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import authservice.authservice.model.oauth.OAuthUser;
import authservice.authservice.repository.OAuthUserRepository;

@Service
public class OAuthUserService {

    @Autowired
    private OAuthUserRepository oAuthUserRepository;


    public void updateUser(OAuthUser currentUser, OAuthUser updatedUser) {

        if (currentUser == null || updatedUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (currentUser.getName() == null || updatedUser.getName() == null) {
            throw new IllegalArgumentException("User name cannot be null");
        }

        if (currentUser.getEmail() == null || updatedUser.getEmail() == null) {
            throw new IllegalArgumentException("User email cannot be null");
        }

        if (currentUser.getBirthdate() == null || updatedUser.getBirthdate() == null) {
            throw new IllegalArgumentException("User birthdate cannot be null");
        }

        if (currentUser.getPhoneNumber() == null || updatedUser.getPhoneNumber() == null) {
            throw new IllegalArgumentException("User phone number cannot be null");
        }

        if (currentUser.getId() == null || updatedUser.getId() == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }


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
