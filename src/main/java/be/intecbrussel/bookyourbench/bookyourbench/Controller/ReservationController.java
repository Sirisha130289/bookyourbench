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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReservationController {

    private ReservationServiceImpl reservationService;

    @Autowired
    private SeatingController seatingController;

    @Autowired
    public void setReservationService(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/viewreservations")
    public String viewReservationPage(HttpSession session, Model model) {
        List<ReservationInfo> reservations = reservationService.viewExistingReservationsForUserId((Integer) session.getAttribute("userId"));

        long bookedCount = reservations.stream().filter(res -> "BOOKED".equals(res.getStatus())).count();
        boolean canReserve = bookedCount < 2;

        model.addAttribute("canReserve", canReserve);
        model.addAttribute("reservations", reservations);

        return "viewreservations";
    }

    @PostMapping("/confirmbooking")
    public String confirmBooking(HttpSession session, Model model, @RequestParam("building") String building, @RequestParam("floor") String floor, @RequestParam("date") String date) {

        int userId = (Integer) session.getAttribute("userId");

        List<ReservationInfo> existingReservation = reservationService.viewExistingReservationsForUserId(userId);

        boolean reservationExists = isReservationAlreadyExisting(userId, date, existingReservation);
        boolean bookingLimitReached = hasReachedReservationLimit(userId, existingReservation);

        if (reservationExists) {
            model.addAttribute("error", "A reservation already exists for the selected date.");
            return seatingController.viewReservationPage(session, model);
        } else if (bookingLimitReached) {
            model.addAttribute("error", "Oops! You have already booked 2 seats and hence can't book more.");
            return seatingController.viewReservationPage(session, model);
        }
        else {
            reservationService.confirmReservation(userId, building, floor, date);
            return this.viewReservationPage(session, model);
        }
    }

    private boolean hasReachedReservationLimit(int userId, List<ReservationInfo> existingReservation) {

        return existingReservation.stream().filter(r -> "BOOKED".equals(r.getStatus())).count() >= 2;
    }

    @PostMapping("/cancelreservation")
    public String cancelReservation(HttpSession session, Model model, @RequestParam("building") String building, @RequestParam("floor") String floor, @RequestParam("date") String date) {

        int userId = (Integer) session.getAttribute("userId");

        reservationService.cancelReservation(userId, building, floor, date);
        return this.viewReservationPage(session, model);
    }

    private boolean isReservationAlreadyExisting(int userId, String date, List<ReservationInfo> existingReservation) {

        return existingReservation.stream().filter(r -> "BOOKED".equals(r.getStatus())).filter(r -> {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String bookedDate = formatter.format(r.getDateOfReservation());
            return date.equals(bookedDate);
        }).findAny().isPresent();
    }
}