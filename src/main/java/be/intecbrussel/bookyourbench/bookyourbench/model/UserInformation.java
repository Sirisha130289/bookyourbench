package be.intecbrussel.bookyourbench.bookyourbench.model;

import java.time.LocalDate;
import java.util.Objects;


public class UserInformation {


    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate lastLoggedIn;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(LocalDate lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInformation)) return false;
        UserInformation that = (UserInformation) o;
        return getUserId() == that.getUserId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getLastLoggedIn(), that.getLastLoggedIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getPassword(), getLastLoggedIn());
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", lastLoggedIn=" + lastLoggedIn +
                '}';
    }
}

