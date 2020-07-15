package be.intecbrussel.bookyourbench.bookyourbench.Controller;

import be.intecbrussel.bookyourbench.bookyourbench.model.Seating;
import be.intecbrussel.bookyourbench.bookyourbench.service.implementations.SeatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SeatingController {
    private SeatingServiceImpl seatingService;

    @Autowired
    public void setSeatingService(SeatingServiceImpl seatingService) {
        this.seatingService = seatingService;
    }

    @GetMapping("/bookseat")
    public String viewReservationPage(HttpSession session, Model model) {
        List<Seating> seatingList = seatingService.viewAvailableSeats();
        System.out.println(seatingList);

        Map<String, Set<Map<String, List<String>>>> buildingFloorMap = new HashMap<>();

        seatingList.stream().forEach(s -> {

            String buildingName = s.getBuildingName();
            Set<Map<String, List<String>>> buildingFloorSet = new HashSet<>();

            seatingList.stream().filter(inner -> buildingName.equals(inner.getBuildingName())).forEach(inner -> {
                Map<String, List<String>> floorDateMap = new HashMap<>();
                String floorNo = inner.getFloorNo();

                List<LocalDate> dateList = seatingList.stream().filter(inner2 -> buildingName.equals(inner2.getBuildingName()))
                        .filter(inner2 -> floorNo.equals(inner2.getFloorNo())).map(Seating::getDate).collect(Collectors.toList());

                List<String> dateStrList = new ArrayList<>();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                dateList.forEach(date -> {
                    System.out.println(date);
                    String text = date.format(dtf);
                    dateStrList.add(text);
                });

                floorDateMap.put(floorNo, dateStrList);



                buildingFloorSet.add(floorDateMap);

                buildingFloorMap.put(buildingName, buildingFloorSet);
            });
        });

        System.out.println("buildingFloorMap : " + buildingFloorMap);
        model.addAttribute("buildingFloorMap", buildingFloorMap);

        return "booking";
    }
}
