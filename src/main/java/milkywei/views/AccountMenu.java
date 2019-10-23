package milkywei.views;

import java.util.List;
import java.util.Scanner;

import milkywei.models.Bank;
import milkywei.models.User;
import milkywei.services.AccountServices;
import milkywei.services.UserServices;
import milkywei.util.ScannerUtil;

public class AccountMenu implements View {

	public static int TargetBank = 0;
	public static String CowName = "";

	private void printMenu() {
		System.out.println("--------------------------------------------");
		System.out.println("------------- Account Menu -----------------");
		System.out.println("-------------- [Cows Menu] -----------------");

		System.out.println("1. Select Bank");
		System.out.println("2. Create Bank");
		System.out.println("3. Connect to Existing Bank");
		System.out.println("4. Delete Bank");
		System.out.println("0. Logout");
	}

	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(4);
		switch (selection) {
		case 0:
			System.out.println("--------------------------------------------");
			System.out.println("--Logged out of: " + MainMenu.TargetUser +" --");
			System.out.println("--------------------------------------------");
			return new MainMenu();
		case 1:
			if (selectBank()) {
				return new BankMenu();
			}
			return new AccountMenu();
		case 2:
			createBank();
			return new AccountMenu();
		case 3:
			connectBank();
			return new AccountMenu();
		case 4:
			if (deleteBank()) {
				System.out.println("--------------------------------------------");
				System.out.println("----" + TargetBank +" Has Been 'Deleted'---");
				System.out.println("--------------------------------------------");
			}
			return new AccountMenu();
		default:
			return null;
		}
	}

	String Username = MainMenu.TargetUser;

	private boolean deleteBank() {
		List<Bank> AvaliableBanks = AccountServices.selectableBank();

		if (AvaliableBanks == null) {
			return false;
		}

		// have user select a bank to be deleted
		System.out.println("Which Cow (Bank) would you like to 'DELETE'?");
		int selector = 1;
		for (int i = 0; i < AvaliableBanks.size(); i++) {
			System.out.println("Press [" + selector++ + "] to 'DELETE': " + AvaliableBanks.get(i).getBankName()
					+ "( CowID #: " + AvaliableBanks.get(i).getBankID());
		}

		selector--;

		try {
			Scanner scanner = new Scanner(System.in);
			int input = -1;
			System.out.println("Please insert the corresponding integer value:");
			if (!scanner.hasNextInt()) {
				scanner.nextLine();
			}
			input = scanner.nextInt();

			TargetBank = AvaliableBanks.get(--input).getBankID();
			input++;
			System.out.println("Selected CowID: " + TargetBank + " Delete Cow? :"
					+ AvaliableBanks.get(--input).getBankName() + "?");

			
			System.out.println("Press [Y] to Confirm Delete");
			System.out.println("Press [N] to Cancel");
			Scanner confirm = new Scanner(System.in);
			// confirm.nextLine();
			String s = confirm.next();
			if (s.equals("Y") || s.equals("y")) {
				AccountServices.deleteAccount(TargetBank);
				return true;
			} else
				System.out.println("Actions Canceled or Invalid Input");
			return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("--------------------------------------------");
			System.out.println("---------- Not A Valid Command -------------");
			System.out.println("--------------------------------------------");
			// e.printStackTrace();
		}

		return false;

	}

	private boolean createBank() {
		System.out.println("--------------------------------------------");
		System.out.println("----------- Account Registration -----------");
		System.out.println("--------------------------------------------");
		System.out.println("Name your Cow: ");
		String BankName = ScannerUtil.getStringInput();
		System.out.println("Enter (banking) type 'savings' or 'checking': ");
		String BankType = ScannerUtil.getStringInput().toLowerCase();

		if (BankName.length() > 50) {
			System.out.println("Sorry, invalid cow name");
			return false;
		}

		if (BankType.equals("savings") || BankType.equals("saving") || BankType.equals("checkings")
				|| BankType.equals("checking")) {
			if (AccountServices.createBank(BankName, BankType)) {
				System.out.println("Congrats, Cow named " + BankName + "  has been given Birth!");
				return true;
			}
		} else {
			System.out.println("Sorry, invalid Cow type, it has to either be savings or checking");
			return false;
		}
		return false;
	};

	private void connectBank() {
		System.out.println("--------------------------------------------");
		System.out.println("-------------- Joint Account ---------------");
		System.out.println("--------------------------------------------");
		System.out.println("To manage an exisiting cow, please provide it's Unique CowID #: ");
		Scanner bankID = new Scanner(System.in);
		// confirm.nextLine();
		int id = bankID.nextInt();

		AccountServices.connectAccounts(Username, id);

	}

	private boolean selectBank() {
		List<Bank> AvaliableBanks = AccountServices.selectableBank();

		if (AvaliableBanks == null) {
			return false;
		}

		// have user select a bank
		System.out.println("Please Select the Cow you would like to Manage: ");

		int selector = 1;
		for (int i = 0; i < AvaliableBanks.size(); i++) {
			System.out.println("Press [" + selector++ + "] to manage Cow named: " + AvaliableBanks.get(i).getBankName()
					+ "( [CowID #: " + AvaliableBanks.get(i).getBankID() + ")");
		}

		selector--;

		try {
			Scanner scanner = new Scanner(System.in);
			int input = -1;

			while (input < 1 || input > selector) {
				if (!scanner.hasNextInt()) {
					scanner.nextLine();
					continue;
				}
				input = scanner.nextInt();

				TargetBank = AvaliableBanks.get(--input).getBankID();
				System.out.println("You inserted [" + ++input + "], you've selected Cow named :"
						+ AvaliableBanks.get(--input).getBankName());
				CowName = AvaliableBanks.get(input).getBankName();
				
				return true;
			}

			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("--------------------------------------------");
			System.out.println("-----------Not A Valid Command--------------");
			System.out.println("--------------------------------------------");
			// e.printStackTrace();
		}
		return false;

	}

}
