package Form;

import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DoanhThu extends JFrame {
	private static Connection conn = null;
	private static String url = "jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "Pvb171717ml";
	
	public static void main(String[] args) throws SQLException {

	    Statement stmt;
	    String query;
	    ResultSet rs;
	 
	    Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"}};
	    Object columnNames[] = {"Column One", "Column Two", "Column Three"};
	 
	    DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
	    JTable table = new JTable(mTableModel);
	 
	    try {
	      Class.forName(driver).newInstance();
	      conn = (Connection) DriverManager.getConnection(url, user, password);
	    } catch (Exception e) {
	      System.err.println("Exception: " + e.getMessage());
	    }
	 
	    // run the desired query
	    query = "SELECT colOne, colTwo, colThree FROM tableName";
	    // make a statement with the server
	    stmt = (Statement) conn.createStatement();
	    // execute the query and return the result
	    rs = stmt.executeQuery(query);
	 
	    // create the gui
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(300, 150);
	    frame.setVisible(true);
	 
	    // remove the temp row
	    mTableModel.removeRow(0);
	 
	    // create a temporary object array to hold the result for each row
	    Object[] rows;
	    // for each row returned
	    while (rs.next()) {
	      // add the values to the temporary row
	      rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)};
	      // add the temp row to the table
	      mTableModel.addRow(rows);
	    }
	  
		
		
		
		
		
		
		
		
		
		
		
		
//		Vector vData = null, vTitle = null;
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
//			Statement stm = (Statement) conn.createStatement();
//			ResultSet rst = stm.executeQuery("SELECT * FROM thongtindaily");
//			ResultSetMetaData rstmeta = (ResultSetMetaData) rst.getMetaData();
//			int num_column = rstmeta.getColumnCount();
//
//			vTitle = new Vector(num_column);
//			for (int i = 1; i <= num_column; i++) {
//				vTitle.add(rstmeta.getColumnLabel(i));
//			}
//
//			vData = new Vector(10, 10);
//			while (rst.next()) {
//				Vector row = new Vector(num_column);
//				for (int i = 1; i <= num_column; i++)
//					row.add(rst.getString(i));
//				vData.add(row);
//			}
//			rst.close();
//			stm.close();
//			conn.close();
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		JScrollPane tableResult = new JScrollPane(new JTable(vData, vTitle));
//
//		JFrame f = new JFrame();
//		f.setSize(600, 480);
//		f.setContentPane(tableResult);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.show();
		
		
		
	}
}
