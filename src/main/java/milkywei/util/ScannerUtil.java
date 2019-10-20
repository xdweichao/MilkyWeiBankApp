package milkywei.util;

import java.util.Scanner;

public class ScannerUtil {
	static Scanner scanner = new Scanner(System.in);

	public static int getInput(int max) {
		int input = -1;

		while (input < 0 || input > max) {
			System.out.println("Please insert an integer value between 0 and " + max);
			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextInt();
		}

		return input;
	}

	public static String getStringInput() {
		String input = "";
		while (input.isEmpty()) {
			input = scanner.nextLine();
		}
		return input;
	}

	public static double getInput(double max) {
		double input = -1;

		while (input < 0 || input > max) {
			System.out.println("Please insert a number value between 0 and " + max);
			if (!scanner.hasNextDouble()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextDouble();

		}
		return input;
	}

}