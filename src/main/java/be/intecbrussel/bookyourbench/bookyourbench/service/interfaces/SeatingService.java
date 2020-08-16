package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SeatingService {
    public Map<String, Set<Map<String, List<String>>>> retrieveAvailableSeats();

    public boolean updateSeat(String status, String building, String floor, LocalDate date);

}
