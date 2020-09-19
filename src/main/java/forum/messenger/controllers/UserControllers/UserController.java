package forum.messenger.controllers.UserControllers;

import forum.messenger.DTO.RegistrationDTO;
import forum.messenger.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    RegistrationService registrationService;

    @Autowired
    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //todo fix validation
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



}
