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

public class PhieuChiTieu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int ngay;
	private int thang;
	private int nam;
	private JComboBox<Integer> ngayChiComboBox;
	private JComboBox<Integer> thangChiComboBox;
	private JComboBox<Integer> namChiComboBox;
	private JTextField khoanChiTextField;
	private JTextField soLuongTextField;
	private JTextField donGiaTextField;
	private JButton themButton;
	private JButton datLaiButton;

	public PhieuChiTieu() {
		setTitle("TẠO CHI TIÊU");
		setSize(430, 340);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel tieuDeLabel = new JLabel("Phiếu Chi");
		tieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tieuDeLabel.setBounds(160, 5, 300, 60);
		add(tieuDeLabel);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngay = cal.get(Calendar.DATE);
		thang = cal.get(Calendar.MONTH)+1;
		nam = cal.get(Calendar.YEAR);
		
		JLabel ngayChiLabel = new JLabel("Ngày:");
		ngayChiLabel.setBounds(65, 75, 99, 20);
		add(ngayChiLabel);

		ngayChiComboBox = new JComboBox<>();
		ngayChiComboBox.setBounds(110, 77, 45, 20);
		for (int i = 1; i <= 31; i++) { ngayChiComboBox.addItem(i); }
		add(ngayChiComboBox);
		ngayChiComboBox.setSelectedItem(ngay);
		
		JLabel thangChiLabel = new JLabel("Tháng:");
		thangChiLabel.setBounds(164, 75, 99, 20);
		add(thangChiLabel);
		
		thangChiComboBox = new JComboBox<>();
		thangChiComboBox.setBounds(210, 77, 44, 20);
		for (int i = 1; i <= 12; i++) { thangChiComboBox.addItem(i); }
		add(thangChiComboBox);
		thangChiComboBox.setSelectedItem(thang);
		
		JLabel namChiLabel = new JLabel("Năm:");
		namChiLabel.setBounds(266, 75, 99, 20);
		add(namChiLabel);
		
		namChiComboBox = new JComboBox<>();
		namChiComboBox.setBounds(300, 77, 59, 20);
		for (int i = nam; i >= 1905; i--) { namChiComboBox.addItem(i); }
		add(namChiComboBox);
		namChiComboBox.setSelectedItem(nam);

		JLabel khoanChiLabel = new JLabel("Khoản Chi:");
		khoanChiLabel.setBounds(34, 113, 99, 20);
		add(khoanChiLabel);

		khoanChiTextField = new JTextField();
		khoanChiTextField.setBounds(110, 113, 250, 25);
		add(khoanChiTextField);
		khoanChiTextField.setColumns(10);

		JLabel soLuongLabel = new JLabel("Số Lượng:");
		soLuongLabel.setBounds(35, 151, 99, 20);
		add(soLuongLabel);

		soLuongTextField = new JTextField();
		soLuongTextField.setBounds(110, 151, 250, 25);
		add(soLuongTextField);
		soLuongTextField.setColumns(10);

		JLabel donGiaLabel = new JLabel("Đơn Giá:");
		donGiaLabel.setBounds(46, 189, 99, 20);
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

	public void ThemChiTieu() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
			Statement stm = (Statement) conn.createStatement();
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO doanh_thu_ve_so.phieu_chi(Ngày_Chi, Khoản_Chi, Số_Lượng, Đơn_Giá, Tiền_Chi) "
					+ "VALUE (?,?,?,?,Số_Lượng*Đơn_Giá);");
			String ngayChi = ""+namChiComboBox.getSelectedItem().toString()+"-"+thangChiComboBox.getSelectedItem().toString()+
					"-"+ngayChiComboBox.getSelectedItem().toString();
			preparedStmt.setString(1, ngayChi);
			preparedStmt.setString(2, khoanChiTextField.getText());
			preparedStmt.setString(3, soLuongTextField.getText());
			preparedStmt.setString(4, donGiaTextField.getText());
			preparedStmt.execute();
			
			try {
				preparedStmt = (PreparedStatement) conn.prepareStatement(
							"INSERT INTO doanh_thu_ve_so.doanh_thu(Ngày, Tiền_Chi, Tiền_Thu, Lợi_Nhuận) "
							+ "VALUE (?,?,0,Tiền_Thu-Tiền_Chi);");
				preparedStmt.setString(1, ngayChi);
				preparedStmt.setDouble(2, Double.parseDouble(soLuongTextField.getText())*Double.parseDouble(donGiaTextField.getText()));
				preparedStmt.execute();

			} catch (Exception e1) {
				preparedStmt = (PreparedStatement) conn.prepareStatement(
						"UPDATE doanh_thu_ve_so.doanh_thu "
						+ "SET Tiền_Chi = ?, Lợi_Nhuận = (Tiền_Thu-Tiền_Chi) WHERE Ngày = ?");
				preparedStmt.setDouble(1, Double.parseDouble(soLuongTextField.getText())*Double.parseDouble(donGiaTextField.getText()));
				preparedStmt.setString(2, ngayChi);
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM phieu_chi");
			
			BangChiTieu.mTableModel.setRowCount(0);
			Object[] rows;
			while (rs.next()) {
				rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), 
									  rs.getString(4), rs.getString(5), rs.getString(6) };
				BangChiTieu.mTableModel.addRow(rows);
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
			ThemChiTieu();
			capNhatDuLieu();
			capNhatDoanhThu();

		} else if (e.getSource() == datLaiButton) {
			ngayChiComboBox.setSelectedItem(ngay);
			thangChiComboBox.setSelectedItem(thang);
			namChiComboBox.setSelectedItem(nam);
			khoanChiTextField.setText("");
			soLuongTextField.setText("");
			donGiaTextField.setText("");
		}
	}

//	public static void main(String[] args) {
//		new PhieuChiTieu();
//	}
}
