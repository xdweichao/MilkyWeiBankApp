package milkywei.views;

import milkywei.util.ScannerUtil;

public class AccountMenu implements View{
	private void printMenu() {
		System.out.println("----- Account Menu ------");
		System.out.println("1. Select Bank");
		System.out.println("2. Create Bank");
		System.out.println("3. Delete Bank");
		System.out.println("0. Logout");
	}
	

	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(3);
		switch (selection) {
			case 0: System.out.println("Logging out..."); return new MainMenu();
			case 1: selectBank(); return new BankMenu();//selectbankDAO //go to bank menu
			case 2: createBank(); //
			case 3: deleteBank();
			default: return null;
		}
}


	private void deleteBank() {
		// TODO Auto-generated method stub
		
	}


	private void createBank() {
		// TODO Auto-generated method stub
		
	}


	private void selectBank() {
		// TODO Auto-generated method stub
		
	}


}
