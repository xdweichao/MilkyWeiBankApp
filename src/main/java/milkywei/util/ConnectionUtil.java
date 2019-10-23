package milkywei.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() {
		
		String url = "jdbc:postgresql://localhost:5432/MilkyWei";
		//String url = "jdbc:postgresql://dbmilkywei.cb4kahoa7axw.us-east-2.rds.amazonaws.com:5432/postgres";
		try {
			//System.out.println(System.getenv("MilkyWei_Username") + "" + System.getenv("MilkyWei_Password"));
			
			return DriverManager.getConnection(url, 
					//"postgres",  "revature");
								System.getenv("MilkyWei_Username"), 
								System.getenv("MilkyWei_Password"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database.");
			return null;
		}
	}
}
