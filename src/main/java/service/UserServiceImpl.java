package service;

import dao.UserDaoImpl;
import exception.DBException;
import model.User;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDaoImpl getUserDaoImpl() {
        return new UserDaoImpl(DBHelper.getConnection());
    }

    @Override
    public boolean insertUser(User user) throws DBException {
        try {
             getUserDaoImpl().insertUser(user);
            return true;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User selectUser(long id) throws DBException {
        return getUserDaoImpl().selectUser(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return getUserDaoImpl().selectAllUsers();
    }

    @Override
    public void deleteUser(long id) throws DBException {
        try {
            getUserDaoImpl().deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DBException {
        try {
            getUserDaoImpl().updateUser(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
