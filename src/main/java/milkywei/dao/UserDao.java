package milkywei.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.models.User;
import milkywei.util.ConnectionUtil;

public class UserDao {
	
	public static List<User> getAllUsers() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM USERS";
			ResultSet resultSet = statement.executeQuery(query);

			List<User> users = new ArrayList<>();

			while (resultSet.next()) {
				User user = extractUser(resultSet);
				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static User extractUser(ResultSet resultSet) throws SQLException {
		String Username = resultSet.getString("Username");
		String User_PW = resultSet.getString("User_PW");

		User user = new User(Username, User_PW);
		return user;
	}
}
