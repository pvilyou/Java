package Program;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class ConnectMySQL {

	public static void main(String[] args) throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/quanlydoanhthuveso?useSSL=false";
			String user = "root";
			String password = "Pvb171717ml";
			
			Connection connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Ok");
		} catch (ClassNotFoundException e) {
			System.out.println("No");
			e.printStackTrace();
		}
	}
}
