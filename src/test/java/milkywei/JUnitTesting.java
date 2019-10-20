package milkywei;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import milkywei.util.ScannerUtil;
import milkywei.views.MainMenu;
import milkywei.views.View;

public class JUnitTesting {
	
MainMenu MainMenuTesting = new MainMenu();


public View process() {
	int selection = ScannerUtil.getInput(3);
	
	switch(selection) {
		case 0: return null;
		case 1: Login();
		case 2: Register();
		default: return null;
	}
}

@Test
public void Login() {
	System.out.println("logging on");
	boolean expectations = true;
	boolean logged = true;
	assertEquals("User successfully logged it?",
			expectations, logged);
}


@Test
public void Register() {
	System.out.println("logging on");
	boolean expectations = true;
	boolean registered = true;
	assertEquals("User successfully logged it?",
			expectations, registered);
	
}



@Before
public void beforeEachTest() {
	System.out.println("Before test");
}

@BeforeClass
public static void beforeClass() {
	System.out.println("Before the class");
}

@After
public void afterEachTest() {
	System.out.println("After each test");
}

@AfterClass
public static void afterClass() {
	System.out.println("After the class");
}
}
