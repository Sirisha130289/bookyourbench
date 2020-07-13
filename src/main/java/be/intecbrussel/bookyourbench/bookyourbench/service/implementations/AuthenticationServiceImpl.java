package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.UserInformationDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserInformationDaoImpl userInformationDaoJDBC;

    @Autowired
    public void setUserInformationDaoJDBC(UserInformationDaoImpl userInformationDaoJDBC) {
        this.userInformationDaoJDBC = userInformationDaoJDBC;
    }

    @Override
    public UserInformation getUser(int id) {
        return userInformationDaoJDBC.getUserById(id);
    }

    @Override
    public List<UserInformation> getAllUsers() {
        return userInformationDaoJDBC.getAllUsers();
    }

    @Override
    public boolean authenticateUser(String password, UserInformation user) {
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
