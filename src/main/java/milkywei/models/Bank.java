package milkywei.models;

import java.math.BigDecimal;

public class Bank {
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bank(int bankID, String bankName, String bankType, BigDecimal bankBalance) {
		super();
		BankID = bankID;
		BankName = bankName;
		BankType = bankType;
		BankBalance = bankBalance;
	}
	@Override
	public String toString() {
		return "Bank [BankID=" + BankID + ", BankName=" + BankName + ", BankType=" + BankType + ", BankBalance="
				+ BankBalance + "]";
	}
	public int getBankID() {
		return BankID;
	}
	public void setBankID(int bankID) {
		BankID = bankID;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getBankType() {
		return BankType;
	}
	public void setBankType(String bankType) {
		BankType = bankType;
	}
	public BigDecimal getBankBalance() {
		return BankBalance;
	}
	public void setBankBalance(BigDecimal bankBalance) {
		BankBalance = bankBalance;
	}
	private int BankID;
	private String BankName;
	private String BankType;
	private BigDecimal BankBalance;
}
