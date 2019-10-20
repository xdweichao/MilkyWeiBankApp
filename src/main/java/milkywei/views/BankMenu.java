package milkywei.views;

import milkywei.util.ScannerUtil;

public class BankMenu implements View{

	private void printMenu() {
		System.out.println("----- Bank Menu ------");
		System.out.println("1. Check Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Transfer");
		System.out.println("0. Back");
	}
	

	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(4);
		switch (selection) {
			case 0: return new AccountMenu();
			case 1: return null;
			case 2: return null;
			case 3: return null;
			case 4: return null;
			default: return null;
		}
}
}