package be.intecbrussel.bookyourbench.bookyourbench.service.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;

import java.util.List;

public interface AuthenticationService {
    public UserInformation getUser(int id);

    public List<UserInformation> getAllUsers();

    public UserInformation authenticateUser(int id, String password);
}

