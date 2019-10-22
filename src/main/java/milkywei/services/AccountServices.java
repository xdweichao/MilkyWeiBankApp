package milkywei.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import milkywei.util.ConnectionUtil;
import milkywei.views.MainMenu;

public class AccountServices {

	public static boolean createBank(String bankName, String bankType) {
try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql ="insert into BANKS (Bank_Name, Bank_Type) values (?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bankName);
			statement.setString(2, bankType);
			statement.executeUpdate();
			
			String sql2 ="insert into ACCOUNTS(FK_USERS_Username, FK_BANKS_Bank_ID) values " + 
					"(?, (SELECT MAX(Bank_ID) AS generatedBankID FROM BANKS));";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, MainMenu.TargetUser);
			statement2.executeUpdate();
			
			return true;

		} catch (SQLException e) {
			//e.printStackTrace();
		
		}
		return false;
	}



}
