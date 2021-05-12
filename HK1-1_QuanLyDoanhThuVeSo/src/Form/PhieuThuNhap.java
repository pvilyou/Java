package Form;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PhieuThuNhap extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int ngay;
	private int thang;
	private int nam;
	private JComboBox<Integer> ngayThuComboBox;
	private JComboBox<Integer> thangThuComboBox;
	private JComboBox<Integer> namThuComboBox;
	private JTextField khoanThuTextField;
	private JTextField soLuongTextField;
	private JTextField donGiaTextField;
	private JButton themButton;
	private JButton datLaiButton;

	public PhieuThuNhap() {
		setTitle("TẠO THU NHẬP");
		setSize(430, 350);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel tieuDeLabel = new JLabel("Phiếu Thu");
		tieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tieuDeLabel.setBounds(160, 5, 300, 60);
		add(tieuDeLabel);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngay = cal.get(Calendar.DATE);
		thang = cal.get(Calendar.MONTH)+1;
		nam = cal.get(Calendar.YEAR);
		
		JLabel ngayChiLabel = new JLabel("Ngày:");
		ngayChiLabel.setBounds(67, 75, 99, 20);
		add(ngayChiLabel);

		ngayThuComboBox = new JComboBox<>();
		ngayThuComboBox.setBounds(110, 77, 45, 20);
		for (int i = 1; i <= 31; i++) { ngayThuComboBox.addItem(i); }
		add(ngayThuComboBox);
		ngayThuComboBox.setSelectedItem(ngay);
		
		JLabel thangChiLabel = new JLabel("Tháng:");
		thangChiLabel.setBounds(163, 75, 99, 20);
		add(thangChiLabel);
		
		thangThuComboBox = new JComboBox<>();
		thangThuComboBox.setBounds(210, 77, 44, 20);
		for (int i = 1; i <= 12; i++) { thangThuComboBox.addItem(i); }
		add(thangThuComboBox);
		thangThuComboBox.setSelectedItem(thang);
		
		JLabel namThuLabel = new JLabel("Năm:");
		namThuLabel.setBounds(266, 75, 99, 20);
		add(namThuLabel);
		
		namThuComboBox = new JComboBox<>();
		namThuComboBox.setBounds(300, 77, 59, 20);
		for (int i = nam; i >= 1905; i--) { namThuComboBox.addItem(i); }
		add(namThuComboBox);
		namThuComboBox.setSelectedItem(nam);

		JLabel khoanThuLabel = new JLabel("Khoản Thu:");
		khoanThuLabel.setBounds(34, 113, 99, 20);
		add(khoanThuLabel);

		khoanThuTextField = new JTextField();
		khoanThuTextField.setBounds(110, 113, 250, 25);
		add(khoanThuTextField);
		khoanThuTextField.setColumns(10);

		JLabel soLuongLabel = new JLabel("Số Lượng:");
		soLuongLabel.setBounds(39, 151, 99, 20);
		add(soLuongLabel);

		soLuongTextField = new JTextField();
		soLuongTextField.setBounds(110, 151, 250, 25);
		add(soLuongTextField);
		soLuongTextField.setColumns(10);

		JLabel donGiaLabel = new JLabel("Đơn Giá:");
		donGiaLabel.setBounds(50, 189, 99, 20);
		add(donGiaLabel);

		donGiaTextField = new JTextField();
		donGiaTextField.setBounds(110, 189, 250, 25);
		add(donGiaTextField);
		donGiaTextField.setColumns(10);

		themButton = new JButton("Thêm Vào");
		themButton.setBounds(105, 244, 90, 25);
		themButton.addActionListener(this);
		add(themButton);

		datLaiButton = new JButton("Đặt Lại");
		datLaiButton.setBounds(222, 244, 90, 25);
		datLaiButton.addActionListener(this);
		add(datLaiButton);

		setVisible(true);
	}

	public void ThemThuNhap() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
			Statement stm = (Statement) conn.createStatement();
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO doanh_thu_ve_so.phieu_thu(Ngày_Thu, Khoản_Thu, Số_Lượng, Đơn_Giá, Tiền_Thu) "
					+ "VALUE (?,?,?,?,Số_Lượng*Đơn_Giá);");
			String ngayThu = ""+namThuComboBox.getSelectedItem().toString()+"-"+thangThuComboBox.getSelectedItem().toString()+
					"-"+ngayThuComboBox.getSelectedItem().toString();
			preparedStmt.setString(1, ngayThu);
			preparedStmt.setString(2, khoanThuTextField.getText());
			preparedStmt.setString(3, soLuongTextField.getText());
			preparedStmt.setString(4, donGiaTextField.getText());
			preparedStmt.execute();

			try {
				preparedStmt = (PreparedStatement) conn.prepareStatement(
							"INSERT INTO doanh_thu_ve_so.doanh_thu(Ngày, Tiền_Chi, Tiền_Thu, Lợi_Nhuận) VALUE (?,0,?,Tiền_Thu-Tiền_Chi);");
				preparedStmt.setString(1, ngayThu);
				preparedStmt.setDouble(2, Double.parseDouble(soLuongTextField.getText())*Double.parseDouble(donGiaTextField.getText()));
				preparedStmt.execute();

			} catch (Exception e1) {
				preparedStmt = (PreparedStatement) conn.prepareStatement(
						"UPDATE doanh_thu_ve_so.doanh_thu SET Tiền_Thu = ?, Lợi_Nhuận = (Tiền_Thu-Tiền_Chi) WHERE Ngày = ?");
				preparedStmt.setDouble(1, Double.parseDouble(soLuongTextField.getText())*Double.parseDouble(donGiaTextField.getText()));
				preparedStmt.setString(2, ngayThu);
				preparedStmt.execute();
			}
			
			stm.close();
			preparedStmt.close();
			conn.close();
			JOptionPane.showMessageDialog(this, "Thêm vào thành công!");
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Không đúng định dạng!");
		}
	}

	public void capNhatDuLieu() {
		try {
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false","root", "Pvb171717ml");

			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM phieu_thu");
			
			BangThuNhap.mTableModel.setRowCount(0);
			Object[] rows;
			while (rs.next()) {
				rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), 
									  rs.getString(4), rs.getString(5), rs.getString(6) };
				BangThuNhap.mTableModel.addRow(rows);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == themButton) {
			ThemThuNhap();
			capNhatDuLieu();
			capNhatDoanhThu();

		} else if (e.getSource() == datLaiButton) {
			ngayThuComboBox.setSelectedItem(ngay);
			thangThuComboBox.setSelectedItem(thang);
			namThuComboBox.setSelectedItem(nam);
			khoanThuTextField.setText("");
			soLuongTextField.setText("");
			donGiaTextField.setText("");
		}
	}

//	public static void main(String[] args) {
//		new PhieuThuNhap();
//	}

}
