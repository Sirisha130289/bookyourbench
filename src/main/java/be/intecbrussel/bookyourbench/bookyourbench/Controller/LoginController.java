package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.AuthenticationServiceImpl;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.ReservationServiceImpl;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    /**
     * We are here declaring a variable authService for the service impl class.
     */
    private AuthenticationServiceImpl authService;

    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private UserInfoService userInfoService;

    // setting injection autowiring
    @Autowired
    public void setAuthService(AuthenticationServiceImpl authService) {
        this.authService = authService;
    }

    /**
     * We are using String as return type which is the name of the template we use in thymeleaf.
     *
     * @return
     */
    @GetMapping("/loginpage")
    public String viewLoginPage() {
        return "loginpage";
    }

    /**
     *
     * @param userId parameters that we require to authenticate
     * @param password  parameters that we require to authenticate
     * @param model this is used in thymeleaf login tenplate for showing login errors
     * @param session this is used to store the login id once the authentication is successful and will be used in next pages.
     * @return
     */
    @PostMapping("/authenticateuser")
    public String authenticateUser(@RequestParam("userid") int userId,
                                   @RequestParam("password") String password, Model model,
                                   HttpSession session) {

        String template = null; //Here we need to redirect to new page if user id and pw match else, stay in the same page.
        // and show an error. so we require 2 templates so, we are initializing a variable template to null.

        UserInformation user = authService.getUser(userId);

        if (user == null) {
            System.out.println("User doesnt exist.");
            model.addAttribute("error", "UserId doesn't exist.");// addattribute() method expects a key value pair jiust like Map.
            // with error as a key, it retrieves the value in template .
            template = "loginpage";
        } else {
            boolean isAuthenticated = authService.authenticateUser(password, user);
            if (!isAuthenticated) {
                System.out.println("Combination of UserId and Password are incorrect.");
                model.addAttribute("error", "Combination of UserId and Password are incorrect.");
                template = "loginpage";
            } else {

                List<ReservationInfo> reservationInfoList = reservationService.viewExistingReservationsForUserId(userId);

                long bookedCount = reservationInfoList.stream().filter(res -> "BOOKED".equals(res.getStatus())).count();
                boolean canReserve = bookedCount < 2;

                System.out.println(user.getFirstName());
                System.out.println("CanReserve : " + canReserve);
                model.addAttribute("username", user.getFirstName());
                model.addAttribute("canReserve", canReserve);
                session.setAttribute("userId", userId);

                userInfoService.updateUserInfo(userId);

                template = "reservationpage";
            }
        }
        return template;
    }
}