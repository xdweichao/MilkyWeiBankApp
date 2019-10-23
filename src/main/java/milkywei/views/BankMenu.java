package milkywei.views;

import java.math.BigDecimal;
import java.util.Scanner;

import milkywei.dao.BankDao;
import milkywei.services.AccountServices;
import milkywei.services.BankServices;
import milkywei.util.ScannerUtil;

public class BankMenu implements View {

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
		case 0:
			return new AccountMenu();
		case 1:
			checkBalance();
			return new BankMenu();
		case 2:
			deposit();
			return new BankMenu();
		case 3:
			withdraw();
			return new BankMenu();
		case 4:
			transfer();
			return new BankMenu();
		default:
			return null;
		}
	}

	private void transfer() {
		System.out.println("Enter the Bank ID of the account you would like to transfer Money to");
		Scanner scanner = new Scanner(System.in);
		int transferTo = scanner.nextInt();
		if (BankDao.checkIfBankExist(transferTo)) {

			System.out.println("Enter how much you would like to Transfer?: ");
			try {
				Scanner scanner2 = new Scanner(System.in);
				BigDecimal WithdrawAmount = new BigDecimal(scanner2.nextLine());
				
				//comparing 2 dec
				//System.out.println(WithdrawAmount.compareTo(BankDao.checkBankBalance(AccountMenu.TargetBank)));

				if (WithdrawAmount.compareTo(BankDao.checkBankBalance(AccountMenu.TargetBank)) <= 0) {
					BankServices.withdraw(AccountMenu.TargetBank, WithdrawAmount);

					BankServices.deposit(transferTo, WithdrawAmount);
				} else
					System.out.println("Invalid Input, Insufficant Funds");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}
	}

	protected void withdraw() {
		System.out.println("Enter how much you would like to withdraw: ");
		try {
			Scanner scanner = new Scanner(System.in);
			BigDecimal WithdrawAmount = new BigDecimal(scanner.nextLine());
			System.out.println(WithdrawAmount.compareTo(BankDao.checkBankBalance(AccountMenu.TargetBank)));

			if (WithdrawAmount.compareTo(BankDao.checkBankBalance(AccountMenu.TargetBank)) <= 0) {
				BankServices.withdraw(AccountMenu.TargetBank, WithdrawAmount);
			} else
				System.out.println("Invalid Input, Insufficant Funds");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	private void deposit() {
		System.out.println("Enter how much you would like to deposit: ");
		try {
			Scanner scanner = new Scanner(System.in);

			BigDecimal DepositAmount = new BigDecimal(scanner.nextLine());
			if (DepositAmount.intValue() >= 0) {

				BankServices.deposit(AccountMenu.TargetBank, DepositAmount);
			} else
				System.out.println("Canceled or Invalid Input");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void checkBalance() {
		BankDao.checkBankBalance(AccountMenu.TargetBank);

	}
}