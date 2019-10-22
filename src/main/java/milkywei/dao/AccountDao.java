package milkywei.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.models.Account;
import milkywei.util.ConnectionUtil;

public class AccountDao {
	public static List<Account> getAllAccounts() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Accounts";
			ResultSet resultSet = statement.executeQuery(query);

			List<Account> accounts = new ArrayList<>();

			while (resultSet.next()) {
				Account account = extractAccounts(resultSet);
				accounts.add(account);
			}

			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Account extractAccounts(ResultSet resultSet) throws SQLException {
		String AccUserName = resultSet.getString("FK_USERS_Username");
		int AccBankID = resultSet.getInt("FK_BANKS_Bank_ID");

		Account account = new Account(AccUserName, AccBankID);
		return account;
	}
}
