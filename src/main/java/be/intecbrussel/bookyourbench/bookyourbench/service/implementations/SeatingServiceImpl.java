package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.SeatingDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.SeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeatingServiceImpl implements SeatingService {

    private SeatingDaoImpl seatingDao;

    @Autowired
    public void setSeatingDao(SeatingDaoImpl seatingDao) {
        this.seatingDao = seatingDao;
    }

    @Override
    public List<Seating> viewAvailableSeats() {
        return seatingDao.retrieveAvailableSeats();
    }

    @Override
    public boolean updateSeat(String status, String building, String floor, LocalDate date) {
        return seatingDao.updateSeatsBookedAndReservableSeats(status, building, floor, date);
    }
}
