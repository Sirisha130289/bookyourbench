package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;

import java.util.List;

public interface ReservationService {
    public List<ReservationInfo> viewExistingReservationsForUserId(int id);

    public List<ReservationInfo> getAllReservations();
}
