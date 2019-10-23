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
		int selection = ScannerUtil.getInput(5);
		switch (selection) {
		case 0:
			System.out.println("Logging out...");
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
		case 4:
			if(selectBank())
			{deleteBank();
			}
			return new AccountMenu();
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
		String BankType = ScannerUtil.getStringInput().toLowerCase();

		if (BankName.length() > 50) {
			System.out.println("Sorry, invalid bank name");
			return false;
		}

		if (BankType.equals("savings") || BankType.equals("saving") || BankType.equals("checkings")
				|| BankType.equals("checking")) {
			if (AccountServices.createBank(BankName, BankType)) {
				System.out.println("Congrats, Bank Account: " + BankName + "  has been created");
				return true;
			}
		} else {
			System.out.println("Sorry, invalid bank type");
			return false;
		}
		return false;
	};

	private void connectBank() {
		// TODO Auto-generated method stub

	}

	private boolean selectBank() {
		String Username = MainMenu.TargetUser;
		List<Bank> AvaliableBanks = AccountServices.selectableBank();

		if(AvaliableBanks ==null) {
			return false;
		}
		
		// have user select a bank
		System.out.println("Please Select A Bank:");

		int selector = 1;
		for (int i = 0; i < AvaliableBanks.size(); i++) {
			System.out.println("Press [" + selector++ + "] to Access: " + AvaliableBanks.get(i).getBankName()
					+ "(Bank ID: " + AvaliableBanks.get(i).getBankID() + ")");
		}

		selector--;

		Scanner scanner = new Scanner(System.in);
		int input = -1;

		while (input < 1 || input > selector) {
			System.out.println("Please insert the corresponding integer value:");
			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextInt();
			
		TargetBank = AvaliableBanks.get(--input).getBankID();
		System.out.println("You inserted [" + ++input + "], you've selected bank :" + AvaliableBanks.get(--input).getBankName());
		System.out.println();
		return true;
		}

		return false;

	}

}
