package Form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import Connection.ConnectMySQL;
import ThongTin.DaiLy;

public class GiaoDien extends JFrame implements ActionListener {

	//private ArrayList<DaiLy> list;
	
	private Container c;		// getContentPane()
	private JLabel TieuDeLabel;
	private JButton DangNhapButton;
	private JButton DangKyButton;
	private JButton jbt1;
	private JButton jbt2;
	private JButton ExitButton;
	private JLabel ThongBaoLabel;
	private JTextField jtf1, jtf2, jtf3;
	private JTable tbl;
	private JPanel p2; 
	
	public GiaoDien() {
		
		setTitle("CHƯƠNG TRÌNH QUẢN LÝ DOANH THU VÉ SỐ");
		setSize(600, 500);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(2,1));
		
//		c = getContentPane();
//		c.setLayout(null);
//		
		
		JPanel p1 = new JPanel();
		Border border1 = BorderFactory.createLineBorder(Color.red);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(border1, "CHƯƠNG TRÌNH QUẢN LÝ DOANH THU VÉ SỐ      "); 
		p1.setBorder(titledBorder1);
		p1.setLayout(new BorderLayout());
		
//		JPanel p11 = new JPanel();
//		p1.add(p11, BorderLayout.WEST);
//		p11.setLayout(new GridLayout(3,1));
//		
//		JLabel jlb1 = new JLabel("Họ Và Tên");
//		p11.add(jlb1);
//		JLabel jlb2 = new JLabel("Quê Quán");
//		p11.add(jlb2);
//		JLabel jlb3 = new JLabel("Lớp");
//		p11.add(jlb3);
//		
//		JPanel p12 = new JPanel();
//		p1.add(p12, BorderLayout.CENTER);
//		
//		p12.setLayout(new GridLayout(3,1));
//		
//		jtf1 = new JTextField(20);
//		p12.add(jtf1);
//		
//		jtf2 = new JTextField(20);
//		p12.add(jtf2);
//		
//		jtf3 = new JTextField(20);
//		p12.add(jtf3);
//
		JPanel p13 = new JPanel();
		p1.add(p13, BorderLayout.AFTER_LAST_LINE);
		
		p13.setLayout(new GridLayout(1,1));
		jbt1 = new JButton("Xem Chi Tiêu");
		jbt1.addActionListener(this);
		p13.add(jbt1);
		
		jbt2 = new JButton("Xem Thu Nhập");
		jbt2.addActionListener(this);
		p13.add(jbt2);
		
//		TieuDeLabel = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ DOANH THU VÉ SỐ");
//		TieuDeLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
//		//TieuDeLabel.setBounds(150, 10, 500, 60);
//		p1.add(TieuDeLabel);
		
//		jbt1.addActionListener(this);
		add(p1);
		
		

		
		p2 = new JPanel();
		Border border2 = BorderFactory.createLineBorder(Color.blue);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(border2, "Doanh Thu"); 
		p2.setBorder(titledBorder2);
		p2.setLayout(new CardLayout());
		
//		dm = new DefaultTableModel();
//		dm.addColumn("Há»� vĂ  tĂªn");
//		dm.addColumn("QuĂª quĂ¡n");
//		dm.addColumn("Lá»›p");
		
//		tbl=new JTable(dm);
//		JScrollPane sc=new JScrollPane(tbl);

		
		Vector vData = null, vTitle = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
			Statement stm = (Statement) conn.createStatement();
			ResultSet rst = stm.executeQuery("SELECT * FROM doanh_thu");
			
			ResultSetMetaData rstmeta = (ResultSetMetaData) rst.getMetaData();
			int num_column = rstmeta.getColumnCount();

			vTitle = new Vector(num_column);
			for (int i = 1; i <= num_column; i++) 
				vTitle.add(rstmeta.getColumnLabel(i));
			
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
		p2.add(tableResult);
		//setContentPane(tableResult);
		show();

		
		
//		DangNhapButton = new JButton("Đăng nhập");
//		DangNhapButton.setBounds(100, 600, 95, 25);
//		DangNhapButton.addActionListener(this);
//		c.add(DangNhapButton);
//		
//		DangKyButton = new JButton("Đăng ký");
//		DangKyButton.setBounds(250, 600, 80, 25);
//		DangKyButton.addActionListener(this);
//		c.add(DangKyButton);
//		
//		ExitButton = new JButton("Thoát");
//		ExitButton.setBounds(400, 600, 80, 25);
//		ExitButton.addActionListener(this);
//		c.add(ExitButton);
		
		add(p2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbt1) {
			new BangChiTieu();
			
		} 
		else if (e.getSource() == jbt2) {
			new BangThuNhap();
		}	
		else if (e.getSource() == ExitButton) {
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		new GiaoDien();
	}
}
