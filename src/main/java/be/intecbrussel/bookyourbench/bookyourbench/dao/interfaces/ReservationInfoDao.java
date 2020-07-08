package be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ReservationInfoDao {
    List<ReservationInfo> allReservations = new ArrayList<>();

    public List<ReservationInfo> viewExistingReservationsForUserId(int id);

    public boolean insertReservation(ReservationInfo reservationInfo);

    public boolean updateReservation(int id, LocalDate date);

    public boolean cancelReservation(int id, LocalDate date);

    public List<ReservationInfo> getAllReservations();
}
