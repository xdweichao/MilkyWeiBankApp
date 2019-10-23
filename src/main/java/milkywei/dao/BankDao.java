package milkywei.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.models.Bank;
import milkywei.util.ConnectionUtil;
import milkywei.views.MainMenu;

public class BankDao {

	public static List<Bank> getAllBanks() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Banks";
			ResultSet resultSet = statement.executeQuery(query);

			List<Bank> Banks = new ArrayList<>();

			while (resultSet.next()) {
				Bank Bank = extractBanks(resultSet);
				Banks.add(Bank);
			}

			return Banks;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Bank> getSelectedBanks() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql3 = "select ACCOUNTS.fk_users_username,banks.* from accounts "
					+ "left join banks on accounts.fk_banks_bank_id = banks.bank_id "
					+ "where accounts.fk_users_username = ?";
			PreparedStatement statement3 = connection.prepareStatement(sql3);
			statement3.setString(1, MainMenu.TargetUser);
			ResultSet resultSet = statement3.executeQuery();

			List<Bank> Banks = new ArrayList<>();

			while (resultSet.next()) {
				Bank Bank = extractBanks(resultSet);
				Banks.add(Bank);
			}
			return Banks;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Bank extractBanks(ResultSet resultSet) throws SQLException {
		int BankID = resultSet.getInt("Bank_ID");
		String BankName = resultSet.getString("Bank_Name");
		String BankType = resultSet.getString("Bank_Type");
		BigDecimal BankBalance = resultSet.getBigDecimal("balance");
		if (BankBalance == null) {
			BankBalance = new BigDecimal(0.00);
		}

		Bank Bank = new Bank(BankID, BankName, BankType, BankBalance);
		return Bank;
	}

	public static boolean checkIfUserHasBankAccounts(String Username) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			// check if user has any banks
			String sql = "select count(FK_USERS_Username) from accounts where FK_USERS_Username = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			if (resultSet.getInt(1) >= 1) {
				return true;
			}
		} catch (SQLException e) {
			// e.printStackTrace();

		}
		return false;
	}

}
