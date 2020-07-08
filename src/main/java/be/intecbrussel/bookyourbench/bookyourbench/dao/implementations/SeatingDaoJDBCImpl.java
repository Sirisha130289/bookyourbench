package be.intecbrussel.bookyourbench.bookyourbench.dao.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces.SeatingDao;
import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeatingDaoJDBCImpl implements SeatingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SEAT_AVAILABILITY = "SELECT *FROM SEATING WHERE DATE=? AND " +
            "TOTAL_RESERVABLE_SEATS>0";
    private static final String SQL_UPDATE = "UPDATE SEATING SET SEATS_BOOKED=?, WHERE id=?";

    @Override
    public List<Seating> retrieveAvailableSeats(String buildingName, String floorNo, LocalDate date) {
        String reserveSql = "SELECT BUILDING_NAME, FLOOR_NO, RESERVABLE_SEATS_REMAINING " +
                "FROM SEATING WHERE RESERVABLE_SEATS_REMAINING>0";
        List<Map<String, Object>> seatList = jdbcTemplate.queryForList(reserveSql);
        List<Seating> seatingList = new ArrayList<>();
        seatList.forEach(stringObjectMap -> {
            Seating seating = new Seating();
            seating.setBuildingName((String) stringObjectMap.get("BUILDING_NAME"));
            seating.setFloorNo((String) stringObjectMap.get("FLOOR_NAME"));
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
    public List<Seating> viewAvailableSeatsAsPerBuildingFloorDate() {

        String viewSql = "SELECT * FROM SEATING WHERE RESERVABLE_SEATS_REMAINING>0";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(viewSql);
        List<Seating> seatingList = new ArrayList<>();
        mapList.forEach(stringObjectMap -> {
            Seating seating = new Seating();
            seating.setBuildingName((String) stringObjectMap.get("BUILDING_NAME"));
            seating.setFloorNo((String) stringObjectMap.get("FLOOR_NAME"));
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
    public boolean updateSeatsBookedAndReservableSeats(int id, LocalDate date) {
        String updateSeats = "UPDATE SEATING SET RESERVABLE_SEATS_REMAINING=RESERVABLE_SEATS_REMAINING-1" +
                "AND SEATS_BOOKED=SEATS_BOOKED+1";
        int result = jdbcTemplate.update(updateSeats);
        if (result > 0) {
            return true;
        }

        return false;
    }
}
