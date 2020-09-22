package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SeatingService {
    public Map<String, Set<Map<String, List<String>>>> retrieveAvailableSeats();

}
