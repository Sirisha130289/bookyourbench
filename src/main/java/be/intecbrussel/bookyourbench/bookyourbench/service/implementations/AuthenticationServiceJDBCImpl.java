package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.UserInformationDaoJDBCImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthenticationServiceJDBCImpl implements AuthenticationService {
    @Autowired
    private UserInformationDaoJDBCImpl userInformationDaoJDBC;

    @Override
    public UserInformation getUser(int id) {

        return userInformationDaoJDBC.getUserById(id);
    }

    @Override
    public List<UserInformation> getAllUsers() {
        return userInformationDaoJDBC.getAllUsers();
    }

    @Override
    public UserInformation authenticateUser(int id, String password) {

        return null;
    }
}
