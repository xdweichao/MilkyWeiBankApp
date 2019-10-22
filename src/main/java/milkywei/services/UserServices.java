package milkywei.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import milkywei.util.ConnectionUtil;

public class UserServices {
	public static boolean registerUser(String Username,String Password){

		if (Password.length() < 6) return false;
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql ="insert into USERS (Username, USER_PW) values (?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			statement.setString(2, Password);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			//e.printStackTrace();
		
		}
		return false;
	}
	
	public static boolean loginUser(String Username,String Password){

		try (Connection connection = ConnectionUtil.getConnection()) {
			
			//check if username exist
			String sql ="select count(Username) from users;";
			PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setString(1, Username);
			System.out.println("before");
			ResultSet resultSet= statement.executeQuery();
			System.out.println(resultSet);
			int exist = 0;
			if (exist == 1 )System.out.println("Username exist");
			return true;

			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return false;
	}
	
	
}
