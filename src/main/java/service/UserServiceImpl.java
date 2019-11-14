package service;

import dao.UserDaoImpl;
import exception.DBException;
import model.User;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl {

    private static UserServiceImpl userService;

    public UserServiceImpl() {
    }

    public static synchronized UserServiceImpl instance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public boolean insertUser(User user) throws DBException {
        try {
            UserServiceImpl.getUserDaoImpl().insertUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    public User selectUser(long id) throws DBException {
        return UserServiceImpl.getUserDaoImpl().selectUser(id);
    }

    public List<User> selectAllUsers() {
        return UserServiceImpl.getUserDaoImpl().selectAllUsers();
    }

    public void deleteUser(long id) throws DBException {
        try {
            UserServiceImpl.getUserDaoImpl().deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user) throws DBException {
        try {
            UserServiceImpl.getUserDaoImpl().updateUser(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }



    private static UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl(DBHelper.getConnection());
    }


}
