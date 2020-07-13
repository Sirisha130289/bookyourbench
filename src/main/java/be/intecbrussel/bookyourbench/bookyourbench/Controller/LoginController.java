package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private AuthenticationServiceImpl authService;

    @Autowired
    public void setAuthService(AuthenticationServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/loginpage")
    public String viewLoginPage() {
        return "loginpage";
    }

    @PostMapping("/authenticateuser")
    public String authenticateUser(@RequestParam("userid") int userId,
                                   @RequestParam("password") String password, Model model,
                                   HttpSession session) {

        String template = null;
        UserInformation user = authService.getUser(userId);

        if (user == null) {
            System.out.println("User doesnt exist.");
            model.addAttribute("error", "UserId doesn't exist.");
            template = "loginpage";
        } else {
            boolean isAuthenticated = authService.authenticateUser(password, user);
            if (!isAuthenticated) {
                System.out.println("Combination of UserId and Password are incorrect.");
                model.addAttribute("error", "Combination of UserId and Password are incorrect.");
                template = "loginpage";
            } else {
                session.setAttribute("userId", userId);
                template = "reservationpage";
            }
        }
        return template;
    }

}
