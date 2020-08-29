package forum.messenger.Services;


import forum.messenger.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrationService {
    UserDetailsManager userDetailsManager;

    @Autowired
    public RegistrationService(UserDetailsService userDetailsService) {
        this.userDetailsManager = (UserDetailsManager) userDetailsService;
    }

    public void createUser(String username, String password, String firstName, String email, LocalDate birthday) {
        User user = new User(username, password, firstName, email, birthday);
        UserDetails userDetails = (UserDetails) user;
        userDetailsManager.createUser(userDetails);
    }

    public boolean usernameIsExist(String username) {
        return userDetailsManager.userExists(username);
    }
}
