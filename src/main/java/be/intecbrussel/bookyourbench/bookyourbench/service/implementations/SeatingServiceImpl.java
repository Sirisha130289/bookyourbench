package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.SeatingDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.SeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeatingServiceImpl implements SeatingService {

    private SeatingDaoImpl seatingDao;

    @Autowired
    public void setSeatingDao(SeatingDaoImpl seatingDao) {
        this.seatingDao = seatingDao;
    }

    @Override
    public Map<String, Set<Map<String, List<String>>>> retrieveAvailableSeats() {

        List<Seating> seatingList = seatingDao.retrieveAvailableSeats();

        Map<String, Set<Map<String, List<String>>>> buildingFloorMap = new HashMap<>();

        seatingList.stream().forEach(s -> this.prepareBuildingFloorMap(s, seatingList, buildingFloorMap));

        return buildingFloorMap;
    }

    @Override
    public boolean updateSeat(String status, String building, String floor, LocalDate date) {
        return seatingDao.updateSeatsBookedAndReservableSeats(status, building, floor, date);
    }

    /**
     * @param s
     * @param seatingList
     * @param buildingFloorMap
     */
    private void prepareBuildingFloorMap(Seating s, List<Seating> seatingList, Map<String, Set<Map<String, List<String>>>> buildingFloorMap) {
        {
            String buildingName = s.getBuildingName();
            Set<Map<String, List<String>>> buildingFloorSet = new HashSet<>();

            seatingList.stream().filter(inner -> buildingName.equals(inner.getBuildingName()))
                    .forEach(inner -> this.prepareFloorDateMap(inner, seatingList, buildingName, buildingFloorSet, buildingFloorMap));
        }
    }

    private void prepareFloorDateMap(Seating inner, List<Seating> seatingList, String buildingName, Set<Map<String, List<String>>> buildingFloorSet, Map<String, Set<Map<String, List<String>>>> buildingFloorMap) {
        {
            Map<String, List<String>> floorDateMap = new HashMap<>();
            String floorNo = inner.getFloorNo();

            List<LocalDate> dateList = seatingList.stream().filter(inner2 -> buildingName.equals(inner2.getBuildingName()))
                    .filter(inner2 -> floorNo.equals(inner2.getFloorNo())).map(Seating::getDate).collect(Collectors.toList());

            List<String> dateStrList = new ArrayList<>();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            dateList.forEach(date -> {
                String text = date.format(dtf);
                dateStrList.add(text);
            });

            floorDateMap.put(floorNo, dateStrList);
            buildingFloorSet.add(floorDateMap);
            buildingFloorMap.put(buildingName, buildingFloorSet);
        }
    }
}
