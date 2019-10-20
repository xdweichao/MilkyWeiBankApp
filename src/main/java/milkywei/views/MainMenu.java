package milkywei.views;


import milkywei.util.ScannerUtil;


public class MainMenu implements View {

	public void printMenu() {
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. About Us");
		System.out.println("0. Quit");;
		
	}
	
	public View process() {
		printMenu();
		int selection = ScannerUtil.getInput(3);
		
		switch(selection) {
			case 0: return null;
			case 1: Login();return new AccountMenu();
			case 2: Register();
			case 3: aboutUs();return new MainMenu();
			default: return null;
		}
}
	public void Login() {
		System.out.println("logged on");
		
	}
	
	public void Register() {
		System.out.println("logged on");
		
	}
	
	public void aboutUs() {
		System.out.println("Stars can't shine without darkiness \n" 
				+ "Just like how the banks doesnt exist without money \n"
				+ "Register Now! \n"
				+ "The MilkyWei Bank is founded on October 2019 by Wei Liang");
		
	}
	
	
}


