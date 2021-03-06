package Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class BangChiTieu extends JFrame implements ActionListener {

	private JScrollPane tableResult;
	
	public BangChiTieu() {
		setTitle("BẢNG CHI TIÊU");
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		JMenuBar mnBar = new JMenuBar();
		JMenu chucNang = new JMenu("Chức Năng");
		
		JMenuItem taoChiTieu = new JMenuItem("Thêm");
		taoChiTieu.addActionListener(this);
		JMenuItem xoaChiTieu = new JMenuItem("Xoá");
		xoaChiTieu.addActionListener(this);
		JMenuItem lamMoi = new JMenuItem("Làm mới");
		lamMoi.addActionListener(this);
		
		chucNang.add(taoChiTieu);
		chucNang.add(xoaChiTieu);
		chucNang.add(lamMoi);
		mnBar.add(chucNang);
		setJMenuBar(mnBar);
		
		Vector vData = null, vTitle = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
			Statement stm = (Statement) conn.createStatement();
			ResultSet rst = stm.executeQuery("SELECT * FROM phieu_chi");
			
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

		tableResult = new JScrollPane(new JTable(vData, vTitle));
		setContentPane(tableResult);
		show();
		
		
		
	
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Thêm")) {
			new PhieuChiTieu();
		}
		else if (e.getActionCommand().equals("Xoá")) {
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
//				Statement stm = (Statement) conn.createStatement();
//				ResultSet rst = stm.executeQuery("DELETE FROM phieu_chi WHERE ID = ''");
//			} catch (Exception e1) {
//				System.out.println(e1.getMessage());
//			}
			
		} 
		else if (e.getActionCommand().equals("Làm mới")) {
			Vector vData = null, vTitle = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
				Statement stm = (Statement) conn.createStatement();
				ResultSet rst = stm.executeQuery("SELECT * FROM phieu_chi");
			
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

			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}

			tableResult = new JScrollPane(new JTable(vData, vTitle));
			setContentPane(tableResult);
			show();
			
		}	
		
	}
	
	private void CustomMouseListener() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new BangChiTieu();
	}
}