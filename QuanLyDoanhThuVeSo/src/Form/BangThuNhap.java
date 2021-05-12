package Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class BangThuNhap extends JFrame implements ActionListener {

	public BangThuNhap() {
		setTitle("BẢNG THU NHẬP");
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		JMenuBar mnBar = new JMenuBar();
		JMenu view = new JMenu("Chức Năng");
		
		JMenuItem students = new JMenuItem("Tạo Thu Nhập");
		students.addActionListener(this);
		JMenuItem teachers = new JMenuItem("Xoá Thu Nhập");
		teachers.addActionListener(this);
		
		view.add(students);
		view.add(teachers);
		mnBar.add(view);
		setJMenuBar(mnBar);
		
		Vector vData = null, vTitle = null;
		String url = "jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false";
		String user = "root";
		String password = "Pvb171717ml";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			Statement stm = (Statement) conn.createStatement();
			ResultSet rst = stm.executeQuery("SELECT * FROM phieu_thu");
			ResultSetMetaData rstmeta = (ResultSetMetaData) rst.getMetaData();
			int num_column = rstmeta.getColumnCount();

			vTitle = new Vector(num_column);
			for (int i = 1; i <= num_column; i++) {
				vTitle.add(rstmeta.getColumnLabel(i));
			}

			vData = new Vector(10, 10);
			while (rst.next()) {
				Vector row = new Vector(num_column);
				for (int i = 1; i <= num_column; i++)
					row.add(rst.getString(i));
				vData.add(row);
			}
			rst.close();
			stm.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JScrollPane tableResult = new JScrollPane(new JTable(vData, vTitle));
		setContentPane(tableResult);
		show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Tạo Thu Nhập")) {
			new PhieuThuNhap();
		} 
		else if (e.getActionCommand().equals("Xoá Thu Nhập")) {
			//new PhieuThuNhap();
		}	
		
	}
	
	public static void main(String[] args) {
		new BangThuNhap();
	}
}