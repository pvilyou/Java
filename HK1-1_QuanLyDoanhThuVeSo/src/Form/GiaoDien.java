package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class GiaoDien extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static DefaultTableModel mTableModel;
	public static JTable table;
	private static String ngay;
	private static String tienChi;
	private static String tienThu;
	private static JComboBox<String> tangGiamComboBox;
	private static JComboBox<String> luaChonComboBox;
	private static JComboBox<Integer> ngay1ComboBox;
	private static JComboBox<Integer> thang1ComboBox;
	private static JComboBox<Integer> nam1ComboBox;
	private static JComboBox<Integer> ngay2ComboBox;
	private static JComboBox<Integer> thang2ComboBox;
	private static JComboBox<Integer> nam2ComboBox;
	
	public GiaoDien() {
		BangDoanhThu();
		BangClick();
	}

	public void BangDoanhThu() {
		setTitle("CHƯƠNG TRÌNH QUẢN LÝ DOANH THU VÉ SỐ");
		setSize(810, 650);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(2, 1));

		JPanel chucNangPanel = new JPanel();
		Border chucNangBorder = BorderFactory.createLineBorder(Color.GREEN);
		TitledBorder chucNangTBoder = BorderFactory.createTitledBorder(chucNangBorder, "");
		chucNangPanel.setBorder(chucNangTBoder);
		chucNangPanel.setLayout(new BorderLayout());
		
		JLabel TieuDeLabel = new JLabel(new ImageIcon("images/javaQLDTVS.png"));
		chucNangPanel.add(TieuDeLabel);
		
		JPanel chucNangPanel1 = new JPanel();
		chucNangPanel.add(chucNangPanel1, BorderLayout.AFTER_LAST_LINE);

		JButton chiTieuButton = new JButton("Xem Chi Tiêu");
		chiTieuButton.addActionListener(this);
		chucNangPanel1.add(chiTieuButton);

		JButton thuNhapButton = new JButton("Xem Thu Nhập");
		thuNhapButton.addActionListener(this);
		chucNangPanel1.add(thuNhapButton);
		
		JButton themButton = new JButton("Thêm");
		themButton.addActionListener(this);
		chucNangPanel1.add(themButton);
		
		JButton xoaButton = new JButton("Xoá");
		xoaButton.addActionListener(this);
		chucNangPanel1.add(xoaButton);
		
		JButton suaButton = new JButton("Sửa");
		suaButton.addActionListener(this);
		chucNangPanel1.add(suaButton);

		JButton timKiemButton = new JButton("Tìm Kiếm");
		timKiemButton.addActionListener(this);
		chucNangPanel1.add(timKiemButton);

		JButton sapXepButton = new JButton("Sắp Xếp");
		sapXepButton.addActionListener(this);
		chucNangPanel1.add(sapXepButton);

		JButton thongKeButton = new JButton("Thống Kê");
		thongKeButton.addActionListener(this);
		chucNangPanel1.add(thongKeButton);
		
		JButton lamMoiButton = new JButton("Làm Mới");
		lamMoiButton.addActionListener(this);
		chucNangPanel1.add(lamMoiButton);
		
		add(chucNangPanel);

		Object duLieuHang[][] = { { "", "", "", "" } };
		Object tenCot[] = { "Ngày", "Tiền Chi", "Tiền Thu", "Lợi Nhuận" };

		mTableModel = new DefaultTableModel(duLieuHang, tenCot);
		table = new JTable(mTableModel);

		truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Ngày");
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		BangClick();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void formSapXep() {
		JFrame sapXepJFrame = new JFrame();
		sapXepJFrame.setTitle("CHỌN CÁCH SẮP XẾP");
		sapXepJFrame.setSize(300, 180);
		sapXepJFrame.setLocationRelativeTo(null);
		sapXepJFrame.setLayout(null);
		
		JButton sapXepJButton = new JButton("Sắp xếp");
		sapXepJButton.setBounds(100, 90, 80, 25);
		sapXepJButton.addActionListener(this);
		sapXepJFrame.add(sapXepJButton);
		
		String tangGiam[] = { "Tăng Dần", "Giảm Dần" };
		tangGiamComboBox = new JComboBox<>(tangGiam);
		tangGiamComboBox.setBounds(30, 40, 90, 23);
		sapXepJFrame.add(tangGiamComboBox);
		
		String luaChon[] = { "Theo Ngày", "Theo Tiền Chi", "Theo Tiền Thu", "Theo Lợi Nhuận" };
		luaChonComboBox = new JComboBox<>(luaChon);
		luaChonComboBox.setBounds(140, 40, 120, 23);
		sapXepJFrame.add(luaChonComboBox);
		
		sapXepJFrame.setVisible(true);
		
	}
	
	public void formThongKe() {
		JFrame thongKeJFrame = new JFrame();
		thongKeJFrame.setTitle("CHỌN THỜI GIAN THỐNG KÊ");
		thongKeJFrame.setSize(430, 210);
		thongKeJFrame.setLocationRelativeTo(null);
		thongKeJFrame.setLayout(null);
		
		JButton thongKeJButton = new JButton("Thống kê");
		thongKeJButton.setBounds(155, 130, 90, 25);
		thongKeJButton.addActionListener(this);
		thongKeJFrame.add(thongKeJButton);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int ngay = cal.get(Calendar.DATE);
		int thang = cal.get(Calendar.MONTH)+1;
		int nam = cal.get(Calendar.YEAR);
		
		JLabel ngay1Label = new JLabel("Từ Ngày:");
		ngay1Label.setBounds(45, 32, 99, 20);
		thongKeJFrame.add(ngay1Label);

		ngay1ComboBox = new JComboBox<>();
		ngay1ComboBox.setBounds(110, 33, 45, 20);
		for (int i = 1; i <= 31; i++) { ngay1ComboBox.addItem(i); }
		thongKeJFrame.add(ngay1ComboBox);
		ngay1ComboBox.setSelectedItem(1);
		
		JLabel thang1Label = new JLabel("Tháng:");
		thang1Label.setBounds(164, 32, 99, 20);
		thongKeJFrame.add(thang1Label);
		
		thang1ComboBox = new JComboBox<>();
		thang1ComboBox.setBounds(210, 33, 44, 20);
		for (int i = 1; i <= 12; i++) { thang1ComboBox.addItem(i); }
		thongKeJFrame.add(thang1ComboBox);
		thang1ComboBox.setSelectedItem(1);
		
		JLabel nam1Label = new JLabel("Năm:");
		nam1Label.setBounds(266, 32, 99, 20);
		thongKeJFrame.add(nam1Label);
		
		nam1ComboBox = new JComboBox<>();
		nam1ComboBox.setBounds(300, 33, 59, 20);
		for (int i = nam; i >= 1905; i--) { nam1ComboBox.addItem(i); }
		thongKeJFrame.add(nam1ComboBox);
		nam1ComboBox.setSelectedItem(nam);
		
		JLabel ngay2Label = new JLabel("Đến Ngày:");
		ngay2Label.setBounds(44, 85, 99, 20);
		thongKeJFrame.add(ngay2Label);

		ngay2ComboBox = new JComboBox<>();
		ngay2ComboBox.setBounds(110, 85, 45, 20);
		for (int i = 1; i <= 31; i++) { ngay2ComboBox.addItem(i); }
		thongKeJFrame.add(ngay2ComboBox);
		ngay2ComboBox.setSelectedItem(ngay);
		
		JLabel thang2Label = new JLabel("Tháng:");
		thang2Label.setBounds(164, 85, 99, 20);
		thongKeJFrame.add(thang2Label);
		
		thang2ComboBox = new JComboBox<>();
		thang2ComboBox.setBounds(210, 85, 44, 20);
		for (int i = 1; i <= 12; i++) { thang2ComboBox.addItem(i); }
		thongKeJFrame.add(thang2ComboBox);
		thang2ComboBox.setSelectedItem(thang);
		
		JLabel nam2Label = new JLabel("Năm:");
		nam2Label.setBounds(266, 85, 99, 20);
		thongKeJFrame.add(nam2Label);
		
		nam2ComboBox = new JComboBox<>();
		nam2ComboBox.setBounds(300, 85, 59, 20);
		for (int i = nam; i >= 1905; i--) { nam2ComboBox.addItem(i); }
		thongKeJFrame.add(nam2ComboBox);
		nam2ComboBox.setSelectedItem(nam);
		
		thongKeJFrame.setVisible(true);
	}
	
	public void BangClick() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int i = table.getSelectedRow();
				mTableModel = (DefaultTableModel) table.getModel();
				ngay = mTableModel.getValueAt(i, 0).toString();
				tienChi = mTableModel.getValueAt(i, 1).toString();
				tienThu = mTableModel.getValueAt(i, 2).toString();
			}
		});
	}

	public void truyVanDuLieu(String query) {
		try {
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			mTableModel.setRowCount(0);
			Object[] rows;
			while (rs.next()) {
				rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) };
				mTableModel.addRow(rows);
			}
			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Xem Chi Tiêu")) {
			new BangChiTieu();

		} else if (e.getActionCommand().equals("Xem Thu Nhập")) {
			new BangThuNhap();
			
		} else if (e.getActionCommand().equals("Thêm")) {
			int i = JOptionPane.showConfirmDialog(this, "Bạn có thể thêm Doanh Thu bằng cách thêm Chi Tiêu hoặc Thu Nhập.\n"
					+ "Bạn vẫn muốn tiếp tục thêm trực tiếp vào bảng?\n ");
			if (i == 0) {
				new PhieuDoanhThu();
			}
			
		} else if (e.getActionCommand().equals("Xoá")) {
			if (table.getSelectedRowCount() == 1) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

					PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(
							"DELETE FROM doanh_thu_ve_so.doanh_thu WHERE Ngày = ?");
					preparedStmt.setString(1, ngay);
					preparedStmt.execute();

					truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Ngày");
					preparedStmt.close();
					conn.close();

				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
				
			} else {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "Không có dữ liệu để xoá!");
				} else {
					JOptionPane.showMessageDialog(this, "Hãy chọn một hàng để xoá!");
				}
			}
			
		} else if (e.getActionCommand().equals("Sửa")) {
			if (table.getSelectedRowCount() == 1) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

					PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE doanh_thu_ve_so.doanh_thu "
							+ "SET Tiền_Chi= ?, Tiền_Thu= ?, Lợi_Nhuận=(Tiền_Thu-Tiền_Chi) WHERE Ngày = ?");
					preparedStmt.setString(1, tienChi);
					preparedStmt.setString(2, tienThu);
					preparedStmt.setString(3, ngay);
					preparedStmt.execute();
					
					preparedStmt.close();
					conn.close();
					JOptionPane.showMessageDialog(this, "Thay đổi dữ liệu thành công!");
					
					truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Ngày");
					
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
				
			} else {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "Không có dữ liệu để sửa!");
				} else {
					JOptionPane.showMessageDialog(this, "Click đúp vào một hàng để sửa!");
				}
			}
			
		} else if (e.getActionCommand().equals("Tìm Kiếm")) {
			String timKiem = JOptionPane.showInputDialog("Nhập thông tin bạn cần Tìm Kiếm:", "");
			if (timKiem.isBlank()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin tìm kiếm!");
			} else {
				truyVanDuLieu("SELECT * FROM doanh_thu WHERE Ngày LIKE \'%" + timKiem + "%\' OR Tiền_Chi = \'" + timKiem + "\' "
						+ "OR Tiền_Thu = \'" + timKiem + "\' OR Lợi_Nhuận = \'" + timKiem + "\' ORDER BY Ngày");
			}
		} else if (e.getActionCommand().equals("Sắp Xếp")) {
			formSapXep();
			
		} else if (e.getActionCommand().equals("Sắp xếp")) {
			String tangGiamLuaChon = "";
			if (tangGiamComboBox.getSelectedItem().toString() == "Giảm Dần") tangGiamLuaChon = "DESC";
			
			if (luaChonComboBox.getSelectedItem().toString().equals("Theo Ngày")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.doanh_thu ORDER BY Ngày " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Tiền Chi")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.doanh_thu ORDER BY Tiền_Chi " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Tiền Thu")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.doanh_thu ORDER BY Tiền_Thu " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Lợi Nhuận")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.doanh_thu ORDER BY Lợi_Nhuận " +tangGiamLuaChon);
			}
			
		} else if (e.getActionCommand().equals("Thống Kê")) {
			formThongKe();
			
		} else if (e.getActionCommand().equals("Thống kê")) {
			String tuNgay = nam1ComboBox.getSelectedItem().toString()+"-"+thang1ComboBox.getSelectedItem().toString()+
					"-"+ngay1ComboBox.getSelectedItem().toString();
			String denNgay = nam2ComboBox.getSelectedItem().toString()+"-"+thang2ComboBox.getSelectedItem().toString()+
					"-"+ngay2ComboBox.getSelectedItem().toString();
			truyVanDuLieu("SELECT \'"+tuNgay+" đến "+denNgay+"\', SUM(Tiền_Chi), SUM(Tiền_Thu), SUM(Lợi_Nhuận) "
					+ "FROM doanh_thu_ve_so.doanh_thu WHERE Ngày BETWEEN \'"+tuNgay+"\' AND \'"+denNgay+"\';");
			
		} else if (e.getActionCommand().equals("Làm Mới")) {
			truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Ngày");
		}
	}

	public static void main(String[] args) {
		new GiaoDien();
	}
}
