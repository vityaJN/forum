package forum.messenger.controllers.UserControllers;

import forum.messenger.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        return "login";
    }
}
