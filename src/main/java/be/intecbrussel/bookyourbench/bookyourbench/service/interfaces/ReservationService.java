package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;

import java.util.List;

public interface ReservationService {
    public List<ReservationInfo> viewExistingReservationsForUserId(int id);

    public boolean confirmReservation(int id, String buildingName, String floor, String date);

    public boolean cancelReservation(int userId, String building, String floor, String date);
}
