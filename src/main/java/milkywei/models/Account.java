package milkywei.models;

public class Account {
	public String getAccUserName() {
		return AccUserName;
	}
	public void setAccUserName(String accUserName) {
		AccUserName = accUserName;
	}
	public int getAccBankID() {
		return AccBankID;
	}
	public void setAccBankID(int accBankID) {
		AccBankID = accBankID;
	}
	@Override
	public String toString() {
		return "Account [AccUserName=" + AccUserName + ", AccBankID=" + AccBankID + "]";
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String accUserName, int accBankID) {
		super();
		AccUserName = accUserName;
		AccBankID = accBankID;
	}
	private String AccUserName;
	private int AccBankID;
	
}
