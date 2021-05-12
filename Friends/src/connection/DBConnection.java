package connection;
import java.sql.*;

public class DBConnection {
	Connection con;
	Statement stmt;
	
	//ResultSet
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStmt() {
		return stmt;
	}
	public ResultSet getClassNameFromClass() {
		ResultSet rs = null;
		try {
			rs = this.stmt.executeQuery("select className from class");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydoanhthuveso?useSSL=false","root","Pvb171717ml");
				this.stmt = con.createStatement();
				//stmt.executeUpdate("insert into lop values(\"183\", \"18T3\")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
			try {
				DBConnection condb = new DBConnection();
				ResultSet rs = condb.getStmt().executeQuery("select * from lop "
						+ "inner join student where "
						+ "lop.idLop = student.idLop");
				
				//them du lieu vao csdl cho cac bang
				while (rs.next()) {
					System.out.print(rs.getString(1)+":");
					System.out.print(rs.getString(2) +":");
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
					System.out.println(rs.getString(5	));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
