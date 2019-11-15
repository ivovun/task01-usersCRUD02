package service;

import exception.DBException;
import model.User;

import java.util.List;

public interface UserService {
    boolean insertUser(User user) throws DBException;

    User selectUser(long id) throws DBException;

    List<User> selectAllUsers();

    void deleteUser(long id) throws DBException;

    void updateUser(User user) throws DBException;
}
