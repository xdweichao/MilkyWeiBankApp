package milkywei;

import milkywei.views.MainMenu;
import milkywei.views.View;

public class Application {

	public static void main(String[] args) {
		System.out.println("Welcome to MilkyWei: A bank made up of stars!");
		View currentView = new MainMenu();
		while (currentView != null) {
			currentView = currentView.process();
		}
		System.out.println("To infinity and beyond!");
	}
}
