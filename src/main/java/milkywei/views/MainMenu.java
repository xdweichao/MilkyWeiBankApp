package milkywei.views;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import milkywei.dao.UserDao;
import milkywei.models.User;
import milkywei.util.*;
import milkywei.services.*;

public class MainMenu implements View {


	public static String TargetUser = null;

	public void printMenu() {
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("0. Quit");
		;

	}

	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(2);

		switch (selection) {
		case 0:
			return null;
		case 1:
			if(Login()) 
				
				return new AccountMenu(); 
			else return new MainMenu(); 
		case 2:
			Register();
			return new MainMenu();
		default:
			return null;
		}
	}

	private void Register() {
		
		System.out.println("--------------------------------------------");
		System.out.println("-----------MilkyWei Registration------------");
		System.out.println("--------------------------------------------");
		
		System.out.println("Enter username: ");
		String Username = ScannerUtil.getStringInput();
		System.out.println("Enter password: ");
		String Password = ScannerUtil.getStringInput();

		if (UserServices.registerUser(Username, Password)) {
			System.out.println("Account has been created, Welcome " + Username);
		}else System.out.println("Sorry, either username exist or password length is shorter then 6");
		};

	private void viewAllUsers() {
		// call dao method to get users
//		List<User> users = UserDao.getAllUsers();

//		printUserList(users);

	}

	public boolean Login() {
		System.out.println("--------------------------------------------");
		System.out.println("--------------MilkyWei Login----------------");
		System.out.println("--------------------------------------------");
		
		System.out.println("Enter username: ");
		String Username = ScannerUtil.getStringInput();

		System.out.println("Enter password: ");
		String Password = ScannerUtil.getStringInput();
		
		if(UserServices.loginUser(Username, Password)) {
			TargetUser=Username;
			return true;
		}else return false;
		

	}

//	private void printUserList(List<User> usersList) {
//		System.out.println("-------------------------- Users -------------------------");
//		System.out.println("|     Username     |         Password          |");
//		for (User users : usersList) {
//			System.out.printf("| %-16s | %-25s |%n", users.getUsername(), users.getUser_PW());
//		}
//		System.out.println("----------------------------------------------------------");
//	}

}
