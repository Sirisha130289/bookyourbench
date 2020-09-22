package be.intecbrussel.bookyourbench.bookyourbench.dao.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces.ReservationInfoDao;
import be.intecbrussel.bookyourbench.bookyourbench.model.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationDaoImpl implements ReservationInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SeatingDaoImpl seatingDao;

    @Override
    public List<ReservationInfo> viewExistingReservationsForUserId(int id) {
        String viewReservations = "SELECT * FROM RESERVATION_INFO WHERE USER_ID=?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(viewReservations, id);
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        mapList.forEach(stringObjectMap -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setBuildingName((String) stringObjectMap.get("BUILDING_NAME"));
            reservationInfo.setFloorNo((String) stringObjectMap.get("FLOOR_NO"));
            reservationInfo.setDateOfReservation(((Date) stringObjectMap.get("DATE_OF_RESERVATION")).toLocalDate());
            reservationInfo.setUserId(id);
            reservationInfo.setStatus((String) stringObjectMap.get("STATUS"));
            reservationInfo.setLastUpdatedDate(((Date) stringObjectMap.get("LAST_UPDATED_DATE")).toLocalDate());
            reservationInfos.add(reservationInfo);
        });
        return reservationInfos;
    }

    @Override
    public boolean insertReservation(ReservationInfo reservationInfo) {
        String statusInsertSQL = "INSERT INTO RESERVATION_INFO VALUES (?,?,?,?,?,?)";
        int result = jdbcTemplate.update(statusInsertSQL, reservationInfo.getUserId(), reservationInfo.getDateOfReservation(), reservationInfo.getBuildingName(),
                reservationInfo.getFloorNo(),
                reservationInfo.getStatus(),
                reservationInfo.getLastUpdatedDate()
        );

        if (result > 0) {
            seatingDao.updateSeatsBookedAndReservableSeats(reservationInfo.getStatus(), reservationInfo.getBuildingName(), reservationInfo.getFloorNo(), reservationInfo.getDateOfReservation());
            return true;
        }

        return false;
    }


    @Override
    public boolean cancelReservation(int id, String buildingName, String floor, LocalDate date) {
        String updateCancelledStatus = "UPDATE RESERVATION_INFO SET STATUS='CANCELLED', LAST_UPDATED_DATE=sysdate() " +
                " WHERE USER_ID=? AND DATE_OF_RESERVATION=? AND BUILDING_NAME=? AND FLOOR_NO=?";
        int result = jdbcTemplate.update(updateCancelledStatus, id, date, buildingName, floor);

        seatingDao.updateSeatsBookedAndReservableSeats("CANCELLED", buildingName, floor, date);

        return true;
    }

}
