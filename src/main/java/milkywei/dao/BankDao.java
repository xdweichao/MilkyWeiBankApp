package milkywei.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.models.Bank;
import milkywei.util.ConnectionUtil;

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
	
	public static Bank extractBanks(ResultSet resultSet) throws SQLException {
		int BankID = resultSet.getInt("Bank_ID");
		String BankName = resultSet.getString("Bank_Name");
		String BankType = resultSet.getString("Bank_Type");
		BigDecimal BankBalance = resultSet.getBigDecimal("balance");
		

		Bank Bank = new Bank(BankID,BankName,BankType,BankBalance);
		return Bank;
	}
}
