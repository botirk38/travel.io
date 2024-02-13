package authservice.authservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import authservice.authservice.model.oauth.OAuthUser;
import authservice.authservice.repository.OAuthUserRepository;


import static org.mockito.Mockito.*;

public class OAuthUserServiceTest {

    @InjectMocks
    private OAuthUserService oAuthUserService;

    @Mock
    private OAuthUserRepository oAuthUserRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateUser() {
        OAuthUser currentUser = new OAuthUser();
        currentUser.setName("Current User");
        currentUser.setEmail("email");
        currentUser.setBirthdate("1990-01-01");
        currentUser.setPhoneNumber("1234567890");

        OAuthUser updatedUser = new OAuthUser();
        updatedUser.setName("Updated User");
        updatedUser.setEmail("updateEmail");
        updatedUser.setBirthdate("1990-01-01");
        updatedUser.setPhoneNumber("1234567890");

        oAuthUserService.updateUser(currentUser, updatedUser);
        verify(oAuthUserRepository, times(1)).save(currentUser);
        assert(currentUser.getName().equals(updatedUser.getName()));
    }

    @Test 
    public void testUpdateUserNullFields() {
        OAuthUser currentUser = new OAuthUser();
        currentUser.setName("Current User");
        currentUser.setEmail("email");
        currentUser.setBirthdate("1990-01-01");
        currentUser.setPhoneNumber("1234567890");
    
        OAuthUser updatedUser = new OAuthUser();
        updatedUser.setName("Updated User");
        updatedUser.setEmail("updateEmail");
        updatedUser.setBirthdate("1990-01-01");
        updatedUser.setPhoneNumber(null);
    
        oAuthUserService.updateUser(currentUser, updatedUser);
    
        verify(oAuthUserRepository, times(1)).save(currentUser);
        assert(currentUser.getName().equals(updatedUser.getName()));
    }


    @Test
    public void testUpdateUserNullUser() {
        OAuthUser currentUser = null;
        OAuthUser updatedUser = new OAuthUser();
        updatedUser.setName("Updated User");
        updatedUser.setEmail("updateEmail");
        updatedUser.setBirthdate("1990-01-01");
        updatedUser.setPhoneNumber("1234567890");

        try {
            oAuthUserService.updateUser(currentUser, updatedUser);
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("User cannot be null"));
        }
    }




}