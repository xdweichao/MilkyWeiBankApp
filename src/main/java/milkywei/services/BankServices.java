package milkywei.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.dao.BankDao;
import milkywei.models.Bank;
import milkywei.util.ConnectionUtil;

public class BankServices {

	public static BigDecimal deposit(int BankID, BigDecimal AmountToBeDeposited) {
		try (
			Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "update banks set balance = balance + ? where bank_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setBigDecimal(1, AmountToBeDeposited);
			statement.setInt(2, BankID);
			statement.executeUpdate();
			
			BigDecimal balance = BankDao.checkBankBalance(BankID);
			

			return balance;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public static BigDecimal withdraw(int BankID, BigDecimal AmountToBeWithdrawn)  {
		try (
				Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "update banks set balance = balance - ? where bank_id = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setBigDecimal(1, AmountToBeWithdrawn);
				statement.setInt(2, BankID);
				statement.executeUpdate();
				
				BigDecimal balance = BankDao.checkBankBalance(BankID);
				

				return balance;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
		}

}
