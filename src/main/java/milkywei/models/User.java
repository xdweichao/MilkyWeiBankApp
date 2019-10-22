package milkywei.models;

public class User {
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUser_PW() {
		return User_PW;
	}
	public void setUser_PW(String user_PW) {
		User_PW = user_PW;
	}
	@Override
	public String toString() {
		return "User [Username=" + Username + ", User_PW=" + User_PW + "]";
	}
	public User(String username, String user_PW) {
		super();
		Username = username;
		User_PW = user_PW;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String Username;
	private String User_PW;
	
 
 
 
}
