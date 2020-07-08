package be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces;

import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;


import java.util.ArrayList;
import java.util.List;

public interface UserInformationDao {
    List<UserInformation> allUsers = new ArrayList<>();

    public UserInformation getUserById(int id);

    public List<UserInformation> getAllUsers();

    public boolean updateUserInformation(int id);
}
