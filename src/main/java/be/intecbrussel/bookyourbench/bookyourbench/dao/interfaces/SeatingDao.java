package be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SeatingDao {

    public List<Seating> retrieveAvailableSeats();

    public List<Seating> viewAvailableSeatsAsPerBuildingFloorDate();

    public boolean updateSeatsBookedAndReservableSeats(String status, String building, String floor, LocalDate date);

}
