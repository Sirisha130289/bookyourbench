package be.intecbrussel.bookyourbench.bookyourbench.dao.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces.SeatingDao;
import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SeatingDaoImpl implements SeatingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_UPDATE = "UPDATE SEATING SET SEATS_BOOKED=?, WHERE id=?";
    private static final String SQL_SEATINGUPDATEBOOKED = "UPDATE SEATING SET LAST_UPDATED_DATE=sysdate(), SEATS_BOOKED=SEATS_BOOKED+1," +
            "RESERVABLE_SEATS_REMAINING=RESERVABLE_SEATS_REMAINING-1" +
            " WHERE BUILDING_NAME=? AND FLOOR_NO=? AND DATE=? AND RESERVABLE_SEATS_REMAINING>0";
    private static final String SQL_SEATINGUPDATECANCELLED = "UPDATE SEATING SET LAST_UPDATED_DATE=sysdate(), SEATS_BOOKED=SEATS_BOOKED-1," +
            "RESERVABLE_SEATS_REMAINING=RESERVABLE_SEATS_REMAINING+1" +
            " WHERE BUILDING_NAME=? AND FLOOR_NO=? AND DATE=? AND RESERVABLE_SEATS_REMAINING < TOTAL_RESERVABLE_SEATS";

    @Override
    public List<Seating> retrieveAvailableSeats() {
        String reserveSql = "SELECT * " +
                "FROM SEATING WHERE RESERVABLE_SEATS_REMAINING>0";
        List<Map<String, Object>> seatList = jdbcTemplate.queryForList(reserveSql);
        List<Seating> seatingList = new ArrayList<>();
        seatList.forEach(stringObjectMap -> {
            Seating seating = new Seating();
            seating.setBuildingName((String) stringObjectMap.get("BUILDING_NAME"));
            seating.setFloorNo((String) stringObjectMap.get("FLOOR_NO"));
            seating.setDate(((Date) stringObjectMap.get("DATE")).toLocalDate());
            seating.setTotalSeats((Integer) stringObjectMap.get("TOTAL_SEATS"));
            seating.setTotalReservableSeats((Integer) stringObjectMap.get("TOTAL_RESERVABLE_SEATS"));
            seating.setSeatsBooked((Integer) stringObjectMap.get("SEATS_BOOKED"));
            seating.setRerservableSeatsRemaining((Integer) stringObjectMap.get("RESERVABLE_SEATS_REMAINING"));
            seatingList.add(seating);
        });
        return seatingList;
    }



    @Override
    public boolean updateSeatsBookedAndReservableSeats(String status, String building, String floor, LocalDate date) {
        int result = 0;
        if ("BOOKED".equals(status)) {
            result = jdbcTemplate.update(SQL_SEATINGUPDATEBOOKED, building, floor, date);
        } else if ("CANCELLED".equals(status)) {
            result = jdbcTemplate.update(SQL_SEATINGUPDATECANCELLED, building, floor, date);
        }
        if (result > 0) {
            return true;
        }
        return false;
    }
}
