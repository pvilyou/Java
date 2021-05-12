package Connection;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;
//
//import ThongTin.DaiLy;
//
//public class ConnectMySQL {
//	
//	private Connection conn;
//	
//	public ConnectMySQL() throws SQLException {
//		String url = "jdbc:mysql://localhost:3306/quanlydoanhthuveso?useSSL=false";
//		String user = "root";
//		String password = "Pvb171717ml";
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			this.conn = (Connection) DriverManager.getConnection(url, user, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	
//	}
//	
//	public boolean addDaiLy(DaiLy d) {
//		
//		String mysql = "INSERT INTO thongtindaily(TenDaiLy, DiaChi, DienThoai, VungMien, TenDangNhap, MatKhau)"
//						+ "VALUES(?,?,?,?,?,?)";
//		try {
//			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(mysql);
//			ps.setString(1, d.getTenDaiLy());
//			ps.setString(2, d.getDiaChi());
//			ps.setString(3, d.getSoDienThoai());
//			ps.setString(4, d.getVungMien());
//			ps.setString(5, d.getTenDangNhap());
//			ps.setString(6, d.getMatKhau());
//			
//			return ps.executeUpdate() > 0;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//	public ArrayList<DaiLy> getListDaiLy() {
//		ArrayList<DaiLy> list = new ArrayList<>();
//		String sql = "SELECT * FROM thongtindaily";
//		
//		try {
//			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				DaiLy d = new DaiLy();
//				d.setTenDaiLy(rs.getString("TenDaiLy"));
//				d.setDiaChi(rs.getString("DiaChi"));
//				d.setSoDienThoai(rs.getString("DienThoai"));
//				d.setVungMien(rs.getString("VungMien"));
//				d.setTenDangNhap(rs.getString("TenDangNhap"));
//				d.setMatKhau(rs.getString("MatKhau"));
//				
//				list.add(d);
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return list;
//	}
//	
//	public 
//	Statement stmt = (Statement) conn.createStatement();
//    String SQL = "SELECT * FROM users WHERE users_name='" +  + "' && users_password='" + password+ "'";
//
//    ResultSet rs = stmt.executeQuery(SQL);
//
//            // Check Username and Password
//    while (rs.next()) {
//        databaseUsername = rs.getString("users_name");
//        databasePassword = rs.getString("users_password");
//    }
//
//    if (name.equals(databaseUsername) && password.equals(databasePassword)) {
//        System.out.println("Successful Login!\n----");
//    } else {
//        System.out.println("Incorrect Password\n----");
//    }
//	
//	
//	
//	public static void main(String[] args) throws SQLException {
//		new ConnectMySQL();
//	}
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class ConnectMySQL {
    private static String DB_URL = "jdbc:mysql://localhost:3306/quanlydoanhthuveso?useSSL=false";
    private static String USER_NAME = "root";
    private static String PASSWORD = "Pvb171717ml";
 
    public static Connection getConnection(String dbURL, String userName, String password) {
    	
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String args[]) {
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = conn.createStatement();
            //ResultSet rs = stmt.executeQuery("select * from student");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) 
//                        + "  " + rs.getString(3));
//            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

