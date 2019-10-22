package milkywei.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() {
		String url = "jdbc:postgresql://dbmilkywei.cb4kahoa7axw.us-east-2.rds.amazonaws.com:5432/postgres";
		try {
			return DriverManager.getConnection(url, "postgres",  "revaturepassword");
//								System.getenv("WEI_USER"), 
//								System.getenv("WEI_PASS"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database.");
			return null;
		}
	}
}
