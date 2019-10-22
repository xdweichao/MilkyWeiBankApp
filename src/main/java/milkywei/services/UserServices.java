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
			String sql ="select count(Username) from users where Username = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			ResultSet resultSet= statement.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1) ==1) {
				//System.out.println("Username exist");
				
				//check if password is the same
				sql ="select User_PW from USERS where Username = ?;";
				statement = connection.prepareStatement(sql);
				statement.setString(1, Username);
				resultSet= statement.executeQuery();
			
				resultSet.next();
				//check password equals
				//System.out.println(resultSet.getString(1));
				//System.out.println(Password);
				if(resultSet.getString(1).equals(Password)) {
					System.out.println("Logged In");
					return true;
				} else System.out.println("Incorrect Password");
				return false;
			}else System.out.println("Username or Password Incorrect");
			System.out.println("---Returning Back To Main Menu---");
			return false;

			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return false;
	}
	
	
}
