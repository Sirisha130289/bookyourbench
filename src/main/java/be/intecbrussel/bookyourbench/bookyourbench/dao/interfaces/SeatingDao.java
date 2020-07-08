package be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SeatingDao {

    public List<Seating> retrieveAvailableSeats(String buildingName, String floorNo, LocalDate date);

    public List<Seating> viewAvailableSeatsAsPerBuildingFloorDate();

    public boolean updateSeatsBookedAndReservableSeats(int id, LocalDate date);

}
