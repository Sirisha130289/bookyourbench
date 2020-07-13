package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.ReservationDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ReservationInfo> getAllReservations() {
        return reservationDao.getAllReservations();
    }
}
