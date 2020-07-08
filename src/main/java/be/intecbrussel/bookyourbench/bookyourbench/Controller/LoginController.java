package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/loginpage")
    public String viewLoginPage() {
        return "loginpage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb") UserInformation user,
                          ModelMap model) {
        model.addAttribute("user_id", user.getUserId());
        model.addAttribute("password", user.getPassword());

        return "users";
    }
}
