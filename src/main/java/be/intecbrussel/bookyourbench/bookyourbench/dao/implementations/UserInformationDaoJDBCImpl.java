package be.intecbrussel.bookyourbench.bookyourbench.dao.implementations;

import be.intecbrussel.bookyourbench.bookyourbench.dao.interfaces.UserInformationDao;
import be.intecbrussel.bookyourbench.bookyourbench.model.UserInformation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserInformationDaoJDBCImpl implements UserInformationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserInformation getUserById(int id) {

        String getUserSql = "SELECT * FROM USER_INFORMATION WHERE USER_ID=?";
        UserInformation userInfo = null;
        try {
            Map<String, Object> userMap = jdbcTemplate.queryForMap(getUserSql, id);

            if (userMap != null && userMap.size() > 0) {
                userInfo = new UserInformation();
                userInfo.setUserId(id);
                userInfo.setPassword((String) userMap.get("PASSWORD"));
                userInfo.setFirstName((String) userMap.get("FIRST_NAME"));
                userInfo.setLastName((String) userMap.get("LAST_NAME"));
                userInfo.setLastLoggedIn(((Date) userMap.get("LAST_LOGGED_IN")).toLocalDate());
            }
        } catch (DataAccessException e) {
            System.out.println("User with id : " + id + " not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving user with id : " + id);
            e.printStackTrace();
        }
        return userInfo;
    }


    @Override
    public List<UserInformation> getAllUsers() {
        String sqlAllUsers =
                " SELECT * FROM user_information";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlAllUsers);
        List<UserInformation> userList = new ArrayList<>();
        list.forEach(usermap -> {
            UserInformation userInfo = new UserInformation();
            userInfo.setUserId(Math.toIntExact((Long) usermap.get("USER_ID")));
            userInfo.setFirstName((String) usermap.get("FIRST_NAME"));
            userInfo.setLastName((String) usermap.get("LAST_NAME"));
            userInfo.setPassword((String) usermap.get("PASSWORD"));
            userInfo.setLastLoggedIn(((Date) usermap.get("LAST_LOGGED_IN")).toLocalDate());
            userList.add(userInfo);

        });
        return userList;
    }

    @Override
    public boolean updateUserInformation(int id) {

        String updateUserInfoSql = "UPDATE USER_INFORMATION SET LAST_LOGGED_IN='SYSDATE' WHERE USER_ID = ?";

        UserInformation userInformation = getUserById(id);
        if (userInformation != null) {
            int executed = jdbcTemplate.update(updateUserInfoSql, userInformation.getFirstName(),
                    userInformation.getUserId());
            if (executed != 0) {
                return true;
            }
        }
        return false;
    }
}