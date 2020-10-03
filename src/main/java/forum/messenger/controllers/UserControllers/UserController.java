package forum.messenger.controllers.UserControllers;

import forum.messenger.DTO.RegistrationDTO;
import forum.messenger.Services.RegistrationService;
import forum.messenger.Services.UserService;
import forum.messenger.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private RegistrationService registrationService;

    private UserService userService;

    @Autowired
    public UserController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    /**
     * user registration
     */
    @PostMapping("")
    public void performRegistration(@Valid @RequestBody RegistrationDTO reg,
                                    BindingResult bindingResult) {

        if (!(reg.getPassword().equals(reg.getPasswordTwo()))) {
            bindingResult.addError(new FieldError("registration_data", "password", "passwords doesn't match"));
            bindingResult.addError(new FieldError("registration_data", "passwordTwo", "passwords doesn't match"));
        }
        if (registrationService.usernameIsExist(reg.getUsername())) {
            bindingResult.addError(new FieldError("registration_data", "username", "user already exists"));
        }

        if (bindingResult.hasErrors()) {

        }
        registrationService.createUser(reg.getUsername(), reg.getPassword(), reg.getFirstName(), reg.getEmail(), reg.getBirthday());
    }

    @GetMapping("")
    public List<User> userList() {
        return userService.userList();
    }
}
