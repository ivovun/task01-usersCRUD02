package dao;

import exception.DBException;
import executor.Executor;
import model.User;
import util.DBHelper;
import util.PropertyReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class UserDaoImpl implements UserDao {
	private Executor executor;


//	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
//			+ " (?, ?, ?);";
//
//	private static final String SELECT_ALL_USERS = "select * from users";
//	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
//	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

	public UserDaoImpl(Connection connection) {
		this.executor = new Executor(connection);
	}

    @Override
	public void insertUser(User user)  throws SQLException {
		executor.execUpdatePrepared("INSERT INTO users (name, email, country) VALUES (?, ?, ?)",
				new Object[] {user.getName(), user.getEmail(), user.getCountry()});
//		System.out.println(INSERT_USERS_SQL);
//		// try-with-resource statement will auto close the connection.
//		try (Connection connection = DBHelper.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//			preparedStatement.setString(1, user.getName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, c);
//			System.out.println(preparedStatement);
//			preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
	}

    @Override
	public User selectUser(Long id) {
		try {
			return executor.execQueryPrepared("select id,name,email,country from users where id =?",
					new Object[] {Long.valueOf(id)}, result -> {
						if (!result.next()) {
							return null;
						}
						return new User(Long.valueOf( result.getString("id")), result.getString("name"),
								result.getString("email"),
								result.getString("country"));
					});
		} catch (SQLException e) {
			printSQLException(e);
			return null;
		}


//		// Step 1: Establishing a Connection
//		try (Connection connection = DBHelper.getConnection();
//			 // Step 2:Create a statement using connection object
//			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
//			preparedStatement.setInt(1, id);
//			System.out.println(preparedStatement);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//
//			// Step 4: Process the ResultSet object.
//			while (rs.next()) {
//				String name = rs.getString("name");
//				String email = rs.getString("email");
//				String country = rs.getString("country");
//				user = new User(id, name, email, country);
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return user;
	}

    @Override
	public List<User> selectAllUsers() {
		try {
			return executor.execQueryPrepared("select * from users where ?", new Object[] {true} , result -> {
				List<User> users = new ArrayList<>();
				while (result.next()) {
					users.add(new User(Long.valueOf(result.getString("id")),result.getString("name"),
							result.getString("email"),
							result.getString("country")));
				}
				return users;
			});
		} catch (SQLException e) {
			return new ArrayList<>();
		}
//		// using try-with-resources to avoid closing resources (boiler plate code)
//		List<User> users = new ArrayList<>();
//		// Step 1: Establishing a Connection
//		try (Connection connection = DBHelper.getConnection();
//
//				// Step 2:Create a statement using connection object
//			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
//			System.out.println(preparedStatement);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//
//			// Step 4: Process the ResultSet object.
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//				String email = rs.getString("email");
//				String country = rs.getString("country");
//				users.add(new User(id, name, email, country));
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return users;
	}

    @Override
	public boolean deleteUser(Long id) throws SQLException {
		executor.execUpdatePrepared("delete from users where id = ?;", new Object[]{id});
		return true;

//		if (getClientByName(client.getName()) == null) {
//		} else {
//			throw new SQLException("already in list");
//		}
//		return true;
//		return rowDeleted;
//		try (Connection connection = DBHelper.getConnection();
//				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
//			statement.setInt(1, id);
//			rowDeleted = statement.executeUpdate() > 0;
//		}
//		return rowDeleted;
	}

    @Override
	public boolean updateUser(User user) throws SQLException {

		executor.execUpdatePrepared("update users set name = ?,email= ?, country =? where id = ?;",
				new Object[] {user.getName(), user.getEmail(), user.getCountry(), user.getId()});
		return true;

//		boolean rowUpdated;
//		try (Connection connection = DBHelper.getConnection();
//				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
//			statement.setString(1, user.getName());
//			statement.setString(2, user.getEmail());
//			statement.setString(3, user.getCountry());
//			statement.setInt(4, user.getId());
//
//			rowUpdated = statement.executeUpdate() > 0;
//		}
//		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
