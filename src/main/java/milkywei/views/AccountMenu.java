package milkywei.views;

import milkywei.services.AccountServices;
import milkywei.services.UserServices;
import milkywei.util.ScannerUtil;

public class AccountMenu implements View {
	private void printMenu() {
		System.out.println("----- Account Menu ------");
		System.out.println("1. Select Bank");
		System.out.println("2. Create Bank");
		System.out.println("4. Connect to Existing Bank");
		System.out.println("5. Delete Bank");
		System.out.println("0. Logout");
	}

	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(4);
		switch (selection) {
		case 0:
			System.out.println("Logging out...");
			return new MainMenu();
		case 1:
			selectBank();
			return new BankMenu();// selectbankDAO //go to bank menu
		case 2:
			createBank();
			return new AccountMenu();
		case 3:
			connectBank();
		case 4:
			deleteBank();
		default:
			return null;
		}
	}

	private void deleteBank() {
		// TODO Auto-generated method stub

	}

	private boolean createBank() {
		System.out.println("-----MilkyWei Bank Creation-----");
		System.out.println("Enter Bank Name: ");
		String BankName = ScannerUtil.getStringInput();
		System.out.println("Enter banking type 'savings' or 'checking': ");
		String BankType = ScannerUtil.getStringInput();
		if (BankType == "savings" ^ BankType == "checking") {
			System.out.println("Sorry, invalid  bank type");
			return false;
		}
		
		if (BankName.length() > 50) {
			System.out.println("Sorry, invalid bank name");
			return false;
		}
		
		
		if (AccountServices.createBank(BankName, BankType)) {
			System.out.println("Congrats, Bank named " + BankName + " has been created");
			return true;
		} else
			System.out.println("Sorry, invalid name or bank type");
		return false;
	};

	private void connectBank() {
		// TODO Auto-generated method stub

	}

	private void selectBank() {
		// TODO Auto-generated method stub

	}

}
