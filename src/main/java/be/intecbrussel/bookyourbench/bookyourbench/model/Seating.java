package be.intecbrussel.bookyourbench.bookyourbench.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Seating {
    private String buildingName;
    private String floorNo;
    private LocalDate date;
    private int totalSeats;
    private int totalReservableSeats;
    private int seatsBooked;
    private int rerservableSeatsRemaining;
    private Date lastUpdatedDate;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public LocalDate getDate() {
                return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalReservableSeats() {
        return totalReservableSeats;
    }

    public void setTotalReservableSeats(int totalReservableSeats) {
        this.totalReservableSeats = totalReservableSeats;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public int getRerservableSeatsRemaining() {
        return rerservableSeatsRemaining;
    }

    public void setRerservableSeatsRemaining(int rerservableSeatsRemaining) {
        this.rerservableSeatsRemaining = rerservableSeatsRemaining;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seating)) return false;
        Seating seating = (Seating) o;
        return getTotalSeats() == seating.getTotalSeats() &&
                getTotalReservableSeats() == seating.getTotalReservableSeats() &&
                getSeatsBooked() == seating.getSeatsBooked() &&
                getRerservableSeatsRemaining() == seating.getRerservableSeatsRemaining() &&
                Objects.equals(getBuildingName(), seating.getBuildingName()) &&
                Objects.equals(getFloorNo(), seating.getFloorNo()) &&
                Objects.equals(getDate(), seating.getDate()) &&
                Objects.equals(getLastUpdatedDate(), seating.getLastUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBuildingName(), getFloorNo(), getDate(), getTotalSeats(), getTotalReservableSeats(), getSeatsBooked(), getRerservableSeatsRemaining(), getLastUpdatedDate());
    }

    @Override
    public String toString() {
        return "Seating{" +
                "buildingName='" + buildingName + '\'' +
                ", floorNo='" + floorNo + '\'' +
                ", date=" + date +
                ", totalSeats=" + totalSeats +
                ", totalReservableSeats=" + totalReservableSeats +
                ", seatsBooked=" + seatsBooked +
                ", rerservableSeatsRemaining=" + rerservableSeatsRemaining +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
