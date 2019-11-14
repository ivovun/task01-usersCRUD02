package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void insertUser(User user)  throws SQLException;

    User selectUser(Long id);

    List<User> selectAllUsers();

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user) throws SQLException;
}
