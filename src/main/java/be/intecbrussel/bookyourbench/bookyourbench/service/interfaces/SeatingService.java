package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;

import java.time.LocalDate;
import java.util.List;

public interface SeatingService {
    public List<Seating> viewAvailableSeats();

    public boolean updateSeat(String status, String building, String floor, LocalDate date);

}
