package be.intecbrussel.bookyourbench.bookyourbench.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class ReservationInfo {
    private int userId;
    private LocalDate dateOfReservation;
    private String buildingName;
    private String floorNo;
    private String status;
    private LocalDate lastUpdatedDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationInfo)) return false;
        ReservationInfo that = (ReservationInfo) o;
        return getUserId() == that.getUserId() &&
                Objects.equals(getDateOfReservation(), that.getDateOfReservation()) &&
                Objects.equals(getBuildingName(), that.getBuildingName()) &&
                Objects.equals(getFloorNo(), that.getFloorNo()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getLastUpdatedDate(), that.getLastUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getDateOfReservation(), getBuildingName(), getFloorNo(), getStatus(), getLastUpdatedDate());
    }

    @Override
    public String toString() {
        return "ReservationInfo{" +
                "userId=" + userId +
                ", dateOfReservation=" + dateOfReservation +
                ", buildingName='" + buildingName + '\'' +
                ", floorNo='" + floorNo + '\'' +
                ", status='" + status + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
