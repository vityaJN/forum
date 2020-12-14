package forum.messenger.controllers.UserControllers;

import forum.messenger.DTO.RegistrationDTO;
import forum.messenger.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     *get user registration view
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registration_data", new RegistrationDTO());
        return "registration";
    }

    /**
     * user registration
     *
     */
    @PostMapping("/perform_registration")
    public String performRegistration(@Valid @ModelAttribute("registration_data") RegistrationDTO reg,
                                      BindingResult bindingResult) {

        if (!(reg.getPassword().equals(reg.getPasswordTwo()))) {
            bindingResult.addError(new FieldError("registration_data", "password", "passwords doesn't match"));
            bindingResult.addError(new FieldError("registration_data", "passwordTwo", "passwords doesn't match"));
        }
        if (registrationService.usernameIsExist(reg.getUsername())) {
            bindingResult.addError(new FieldError("registration_data", "username", "user already exists"));
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.createUser(reg.getUsername(), reg.getPassword(), reg.getFirstName(), reg.getEmail(), reg.getBirthday());
        return "redirect:/";
    }
}
