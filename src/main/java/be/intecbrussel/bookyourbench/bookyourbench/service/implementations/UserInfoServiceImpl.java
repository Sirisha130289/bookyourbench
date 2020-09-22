package be.intecbrussel.bookyourbench.bookyourbench.service.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.implementations.UserInformationDaoImpl;
import be.intecbrussel.bookyourbench.bookyourbench.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInformationDaoImpl userInformationDao;

    @Override
    public void updateUserInfo(int id) {
        userInformationDao.updateUserInformation(id);
    }
}
