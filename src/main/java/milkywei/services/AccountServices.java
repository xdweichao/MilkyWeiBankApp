package milkywei.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import milkywei.dao.BankDao;
import milkywei.dao.UserDao;
import milkywei.models.Bank;
import milkywei.models.User;
import milkywei.util.ConnectionUtil;
import milkywei.util.ScannerUtil;
import milkywei.views.MainMenu;

public class AccountServices {

	public static boolean createBank(String bankName, String bankType) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "insert into BANKS (Bank_Name, Bank_Type) values (?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bankName);
			statement.setString(2, bankType);
			statement.executeUpdate();

			String sql2 = "insert into ACCOUNTS(FK_USERS_Username, FK_BANKS_Bank_ID) values "
					+ "(?, (SELECT MAX(Bank_ID) AS generatedBankID FROM BANKS));";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, MainMenu.TargetUser);
			statement2.executeUpdate();

			return true;

		} catch (SQLException e) {
			// e.printStackTrace();

		}
		return false;
	}

	public static List<Bank> selectableBank() {
		// select account name, account id, from accounts where user=username
		// ask for user input
		// if array.contains is true,
		// go to bank menu
		if (BankDao.checkIfUserHasBankAccounts(MainMenu.TargetUser)) {
			List<Bank> BankList = BankDao.getSelectedBanks();

			printSelectableBankList(BankList);

			return BankList;
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("---You currently don't have any bank accounts open---");
		System.out.println("-----------------------------------------------------");
		return null;
	}

	private static void printSelectableBankList(List<Bank> BankList) {

		// display selectable banks that are the users
		System.out.println("---------------------- Selectable Banks ---------------------------------");
		System.out.println("|     Bank ID      |         Bank Name         |       Bank Balance    |");
		for (Bank banks : BankList) {
			System.out.printf("| %-16d | %-25s | " + banks.getBankBalance().setScale(2, BigDecimal.ROUND_DOWN) + " |%n",
					banks.getBankID(), banks.getBankName());
		}
		System.out.println("-------------------------------------------------------------------------");

	}

	public static void deleteAccount(int targetBank) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "delete from accounts where fk_banks_bank_id = ?;" + "delete from  banks where bank_id = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, targetBank);
			statement.setInt(2, targetBank);
			statement.executeUpdate();

		} catch (SQLException e) {
			// e.printStackTrace();

		}
	}

	public static void connectAccounts(String Username, int bankID) {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "insert into accounts (FK_USERS_Username, fk_banks_bank_id) values (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			statement.setInt(2, bankID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// e.printStackTrace();

		}
	}

}
