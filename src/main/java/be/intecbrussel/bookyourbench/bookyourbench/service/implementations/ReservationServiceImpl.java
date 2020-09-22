package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.ReservationDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDaoImpl reservationDao;

    @Override
    public List<ReservationInfo> viewExistingReservationsForUserId(int id) {
        return reservationDao.viewExistingReservationsForUserId(id);
    }


    @Override
    public boolean confirmReservation(int id, String buildingName, String floor, String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        ReservationInfo reservation = new ReservationInfo();
        reservation.setLastUpdatedDate(LocalDate.now());
        reservation.setStatus("BOOKED");
        reservation.setUserId(id);
        reservation.setFloorNo(floor);
        reservation.setDateOfReservation(LocalDate.parse(date, formatter));
        reservation.setBuildingName(buildingName);

        return reservationDao.insertReservation(reservation);
    }

    @Override
    public boolean cancelReservation(int id, String buildingName, String floor, String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        ReservationInfo reservation = new ReservationInfo();
        reservation.setLastUpdatedDate(LocalDate.now());
        reservation.setStatus("BOOKED");
        reservation.setUserId(id);
        reservation.setFloorNo(floor);
        reservation.setDateOfReservation(LocalDate.parse(date, formatter));
        reservation.setBuildingName(buildingName);

        return reservationDao.cancelReservation(id, buildingName, floor, LocalDate.parse(date, formatter));
    }
}