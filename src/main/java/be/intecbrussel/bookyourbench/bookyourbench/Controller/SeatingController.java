package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.SeatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SeatingController {
    private SeatingServiceImpl seatingService;

    @Autowired
    public void setSeatingService(SeatingServiceImpl seatingService) {
        this.seatingService = seatingService;
    }

    @GetMapping("/bookseat")
    public String viewReservationPage(HttpSession session, Model model) {
        List<Seating> seatingList = seatingService.viewAvailableSeats();
        System.out.println(seatingList);
        model.addAttribute("bookseat", seatingList);

        return "booking";
    }
}
