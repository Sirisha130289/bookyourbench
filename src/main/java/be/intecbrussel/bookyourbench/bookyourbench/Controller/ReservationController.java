package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReservationController {

    private ReservationServiceImpl reservationService;

    @Autowired
    public void setReservationService(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/viewreservations")
    public String viewReservationPage(HttpSession session, Model model) {
        List<ReservationInfo> reservations = reservationService.viewExistingReservationsForUserId((Integer) session.getAttribute("userId"));

        model.addAttribute("reservations", reservations);

        return "viewreservations";
    }

    @PostMapping("/confirmbooking")
    public String confirmBooking(HttpSession session, Model model, @RequestParam("building") String building, @RequestParam("floor") String floor, @RequestParam("date") String date) {

        int userId = (Integer) session.getAttribute("userId");

        reservationService.confirmReservation(userId, building, floor, date);

        return this.viewReservationPage(session, model);
    }

    @PostMapping("/cancelreservation")
    public String cancelReservation(HttpSession session, Model model, @RequestParam("building") String building, @RequestParam("floor") String floor, @RequestParam("date") String date) {

        int userId = (Integer) session.getAttribute("userId");

        reservationService.cancelReservation(userId, building, floor, date);
        return this.viewReservationPage(session, model);
    }
}