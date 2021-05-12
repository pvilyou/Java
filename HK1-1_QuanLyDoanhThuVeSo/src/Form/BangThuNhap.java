package Form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BangThuNhap extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static DefaultTableModel mTableModel;
	public static JTable table;
	public static int id;
	public static String ngayThu;
	public static String khoanThu;
	public static String soLuong;
	public static String donGia;
	private static JComboBox<String> tangGiamComboBox;
	private static JComboBox<String> luaChonComboBox;
	private static JComboBox<Integer> ngay1ComboBox;
	private static JComboBox<Integer> thang1ComboBox;
	private static JComboBox<Integer> nam1ComboBox;
	private static JComboBox<Integer> ngay2ComboBox;
	private static JComboBox<Integer> thang2ComboBox;
	private static JComboBox<Integer> nam2ComboBox;

	public BangThuNhap() {
		BangThu();
		TableClick();
	}

	public void BangThu() {

		setTitle("BẢNG THU NHẬP");
		setSize(810, 650);
		setLocationRelativeTo(null);

		JMenuBar menu = new JMenuBar();
		JMenu chucNangMenu = new JMenu("Chức Năng");

		JMenuItem taoThuNhap = new JMenuItem("Thêm");
		taoThuNhap.addActionListener(this);
		JMenuItem xoaThuNhap = new JMenuItem("Xoá");
		xoaThuNhap.addActionListener(this);
		JMenuItem suaThuNhap = new JMenuItem("Sửa");
		suaThuNhap.addActionListener(this);
		JMenuItem timKiem = new JMenuItem("Tìm Kiếm");
		timKiem.addActionListener(this);
		JMenuItem sapXep = new JMenuItem("Sắp Xếp");
		sapXep.addActionListener(this);
		JMenuItem thongKe = new JMenuItem("Thống Kê");
		thongKe.addActionListener(this);
		JMenuItem lamMoi = new JMenuItem("Làm Mới");
		lamMoi.addActionListener(this);
		JMenuItem huongDan = new JMenuItem("Hướng Dẫn");
		huongDan.addActionListener(this);

		chucNangMenu.add(taoThuNhap);
		chucNangMenu.add(xoaThuNhap);
		chucNangMenu.add(suaThuNhap);
		chucNangMenu.add(timKiem);
		chucNangMenu.add(sapXep);
		chucNangMenu.add(thongKe);
		chucNangMenu.add(lamMoi);
		chucNangMenu.add(huongDan);
		menu.add(chucNangMenu);

		setJMenuBar(menu);

		Object duLieuHang[][] = { { "", "", "", "", "", "" } };
		Object tenCot[] = { "ID", "Ngày Thu", "Khoản Thu", "Số Lượng", "Đơn Giá", "Số Tiền Thu" };

		mTableModel = new DefaultTableModel(duLieuHang, tenCot);
		table = new JTable(mTableModel);
		
		mTableModel.setRowCount(0);
		truyVanDuLieu("SELECT * FROM phieu_thu");
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
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
		
		String luaChon[] = { "Theo ID", "Theo Ngày", "Theo Khoản Thu", "Theo Số Lượng", "Theo Đơn Giá", "Theo Tiền Thu" };
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
	
	public void truyVanDuLieu(String query) {
		try {
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			mTableModel.setRowCount(0);
			Object[] rows;
			while (rs.next()) {
				rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), 
						  			  rs.getString(4), rs.getString(5), rs.getString(6)  };
				mTableModel.addRow(rows);
			}
			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
	}
	
	public void capNhatDoanhThu() {
		try {
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM doanh_thu_ve_so.doanh_thu ORDER BY Ngày");

			GiaoDien.mTableModel.setRowCount(0);
			Object[] rows;
			while (rs.next()) {
				rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				GiaoDien.mTableModel.addRow(rows);
			}
			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
	}
	
	public void TableClick() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int i = table.getSelectedRow();
				mTableModel = (DefaultTableModel) table.getModel();
				id = Integer.parseInt(mTableModel.getValueAt(i, 0).toString());
				ngayThu = mTableModel.getValueAt(i, 1).toString();
				khoanThu = mTableModel.getValueAt(i, 2).toString();
				soLuong = mTableModel.getValueAt(i, 3).toString();
				donGia 	= mTableModel.getValueAt(i, 4).toString();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Sự kiện của Menu Chức Năng
		if (e.getActionCommand().equals("Thêm")) {
			new PhieuThuNhap();
			mTableModel.setRowCount(0);
			truyVanDuLieu("SELECT * FROM phieu_thu");
			capNhatDoanhThu();

		} else if (e.getActionCommand().equals("Xoá")) {
			if (table.getSelectedRowCount() == 1) {
				try {
					Connection conn = (Connection) DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");

					PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(
							"DELETE FROM doanh_thu_ve_so.phieu_thu WHERE ID_Thu = ?");
					preparedStmt.setInt(1, id);
					preparedStmt.execute();

					try {
						preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE doanh_thu_ve_so.doanh_thu "
								+ "SET Tiền_Thu = ?, Lợi_Nhuận = (Tiền_Thu-Tiền_Chi) WHERE Ngày = ?");
						preparedStmt.setDouble(1, 0);
						preparedStmt.setString(2, ngayThu);
					} catch (Exception e2) {
						System.err.println(e2.getMessage());
					}
					
					mTableModel.setRowCount(0);
					truyVanDuLieu("SELECT * FROM phieu_thu");
					capNhatDoanhThu();
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

					PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE doanh_thu_ve_so.phieu_thu "
							+ "SET Ngày_Thu= ?, Khoản_Thu= ?, Số_Lượng= ?, Đơn_Giá= ?, Tiền_Thu= Số_Lượng*Đơn_Giá WHERE ID_Thu = ?");
					preparedStmt.setString(1, ngayThu);
					preparedStmt.setString(2, khoanThu);
					preparedStmt.setString(3, soLuong);
					preparedStmt.setString(4, donGia);
					preparedStmt.setInt(5, id);
					preparedStmt.execute();
					
					try {
						preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE doanh_thu_ve_so.doanh_thu "
								+ "SET Tiền_Thu = ?, Lợi_Nhuận = (Tiền_Thu-Tiền_Chi) WHERE Ngày = ?");
						preparedStmt.setDouble(1, Double.parseDouble(soLuong)*Double.parseDouble(donGia));
						preparedStmt.setString(2, ngayThu);
					} catch (Exception e2) {
						System.err.println(e2.getMessage());
					}
					
					mTableModel.setRowCount(0);
					truyVanDuLieu("SELECT * FROM phieu_thu");
					capNhatDoanhThu();
					preparedStmt.close();
					conn.close();
					JOptionPane.showMessageDialog(this, "Thay đổi dữ liệu thành công!");

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
				truyVanDuLieu("SELECT * FROM phieu_thu WHERE ID_Thu = \'" + timKiem + "\' OR Ngày_Thu LIKE \'%" + timKiem + "%\' "
						+ "OR Khoản_Thu = \'" + timKiem + "\' OR Số_Lượng = \'" + timKiem + "\' "
						+ "OR Đơn_Giá = \'" + timKiem + "\' OR Tiền_Thu = \'" + timKiem + "\' ORDER BY ID_Thu");
			}
			
		} else if (e.getActionCommand().equals("Sắp Xếp")) {
			formSapXep();
			
		} else if (e.getActionCommand().equals("Sắp xếp")) {
			String tangGiamLuaChon = "";
			if (tangGiamComboBox.getSelectedItem().toString() == "Giảm Dần") tangGiamLuaChon = "DESC";
			
			if (luaChonComboBox.getSelectedItem().toString().equals("Theo ID")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY ID_Thu " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Ngày")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY Ngày_Thu " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Khoản Thu")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY Khoản_Thu " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Số Lượng")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY Số_Lượng " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Đơn Giá")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY Đơn_Giá " +tangGiamLuaChon);
				
			} else if (luaChonComboBox.getSelectedItem().toString().equals("Theo Tiền Thu")) {
				truyVanDuLieu("SELECT * FROM doanh_thu_ve_so.phieu_thu ORDER BY Tiền_Thu " +tangGiamLuaChon);
			}
			
		} else if (e.getActionCommand().equals("Thống Kê")) {
			formThongKe();
			
		} else if (e.getActionCommand().equals("Thống kê")) {
			String tuNgay = nam1ComboBox.getSelectedItem().toString()+"-"+thang1ComboBox.getSelectedItem().toString()+
					"-"+ngay1ComboBox.getSelectedItem().toString();
			String denNgay = nam2ComboBox.getSelectedItem().toString()+"-"+thang2ComboBox.getSelectedItem().toString()+
					"-"+ngay2ComboBox.getSelectedItem().toString();
			truyVanDuLieu("SELECT \'1\', \'"+tuNgay+" đến "+denNgay+"\', Khoản_Thu, SUM(Số_Lượng), Đơn_Giá, SUM(Tiền_Thu) "
					+ "FROM doanh_thu_ve_so.phieu_thu WHERE Ngày_Thu BETWEEN \'"+tuNgay+"\' AND \'"+denNgay+"\';");
			
		} else if (e.getActionCommand().equals("Làm Mới")) {
			truyVanDuLieu("SELECT * FROM phieu_thu");
			
		} else if (e.getActionCommand().equals("Hướng Dẫn")) {
			JOptionPane.showMessageDialog(this, "Chào bạn, sau đây là hướng dẫn sử dụng một số chức năng cho Bảng Thu Nhập.\n\n"
					+ "Menu \"Chức Năng\" :\n"
					+ "     Thêm: thêm một thu nhập bất kì vào bảng\n"
					+ "     Xoá : click vào một thu nhập và nhấn \"Xoá\" để xoá thu nhập đó ra khỏi bảng\n"
					+ "     Sửa : click đúp vào một thu nhập, thay đổi rồi nhấn \"Sửa\" để sửa thu nhập đó\n"
					+ "     Tìm kiếm : nhập thông tin bạn muốn tìm kiếm rồi nhấn OK\n"
					+ "     Sắp xếp : làm theo hương dẫn, nhập các lựa chọn của bạn để sắp xếp tăng hoặc giảm dần\n"
					+ "     Thống Kê: nhập ngày bắt đầu rồi nhấn OK, \n"
					+ "                        sau đó nhập tiếp ngày kết thúc để thống kê thu nhập trong khoảng thời gian đó\n"
					+ "     Làm Mới: làm mới lại dữ liệu của bảng\n\n"
					+ "Nếu có vấn để cần giải đáp, hãy liên hệ gmail: pvbang.20it1@vku.udn.vn");
		}
	}

//	public static void main(String[] args) throws Exception {
//		new BangThuNhap();
//	}
}