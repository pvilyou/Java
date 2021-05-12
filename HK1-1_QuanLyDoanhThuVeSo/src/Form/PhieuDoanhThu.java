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

public class PhieuDoanhThu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int ngay;
	private int thang;
	private int nam;
	private JComboBox<Integer> ngayComboBox;
	private JComboBox<Integer> thangComboBox;
	private JComboBox<Integer> namComboBox;
	private JTextField tienChiTextField;
	private JTextField tienThuTextField;
	private JButton themButton;
	private JButton datLaiButton;

	public PhieuDoanhThu() {
		setTitle("TẠO DOANH THU");
		setSize(430, 320);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel tieuDeLabel = new JLabel("Doanh Thu");
		tieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tieuDeLabel.setBounds(150, 5, 300, 60);
		add(tieuDeLabel);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		ngay = cal.get(Calendar.DATE);
		thang = cal.get(Calendar.MONTH)+1;
		nam = cal.get(Calendar.YEAR);
		
		JLabel ngayLabel = new JLabel("Ngày:");
		ngayLabel.setBounds(60, 75, 99, 20);
		add(ngayLabel);
		
		ngayComboBox = new JComboBox<>();
		ngayComboBox.setBounds(110, 77, 45, 20);
		for (int i = 1; i <= 31; i++) { ngayComboBox.addItem(i); }
		add(ngayComboBox);
		ngayComboBox.setSelectedItem(ngay);
		
		JLabel thangLabel = new JLabel("Tháng:");
		thangLabel.setBounds(163, 75, 99, 20);
		add(thangLabel);
		
		thangComboBox = new JComboBox<>();
		thangComboBox.setBounds(210, 77, 44, 20);
		for (int i = 1; i <= 12; i++) { thangComboBox.addItem(i); }
		add(thangComboBox);
		thangComboBox.setSelectedItem(thang);
		
		JLabel namLabel = new JLabel("Năm:");
		namLabel.setBounds(266, 75, 99, 20);
		add(namLabel);
		
		namComboBox = new JComboBox<>();
		namComboBox.setBounds(300, 77, 59, 20);
		for (int i = nam; i >= 1905; i--) { namComboBox.addItem(i); }
		add(namComboBox);
		namComboBox.setSelectedItem(nam);

		JLabel tienChiLabel = new JLabel("Tiền Chi:");
		tienChiLabel.setBounds(40, 113, 99, 20);
		add(tienChiLabel);

		tienChiTextField = new JTextField();
		tienChiTextField.setBounds(110, 113, 250, 25);
		add(tienChiTextField);
		tienChiTextField.setColumns(10);

		JLabel tienThuLabel = new JLabel("Tiền Thu:");
		tienThuLabel.setBounds(38, 151, 99, 20);
		add(tienThuLabel);

		tienThuTextField = new JTextField();
		tienThuTextField.setBounds(110, 151, 250, 25);
		add(tienThuTextField);
		tienThuTextField.setColumns(10);

		themButton = new JButton("Thêm Vào");
		themButton.setBounds(105, 210, 90, 25);
		themButton.addActionListener(this);
		add(themButton);

		datLaiButton = new JButton("Đặt Lại");
		datLaiButton.setBounds(222, 210, 90, 25);
		datLaiButton.addActionListener(this);
		add(datLaiButton);
		setVisible(true);
	}

	public void ThemDoanhThu() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
			Statement stm = (Statement) conn.createStatement();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO doanh_thu_ve_so.doanh_thu(Ngày, Tiền_Chi, Tiền_Thu, Lợi_Nhuận) "
					+ "VALUE (?,?,?,Tiền_Thu-Tiền_Chi);");
			String ngay = ""+namComboBox.getSelectedItem().toString()+"-"+thangComboBox.getSelectedItem().toString()+
					"-"+ngayComboBox.getSelectedItem().toString();
			pstm.setString(1, ngay);
			pstm.setString(2, tienChiTextField.getText());
			pstm.setString(3, tienThuTextField.getText());
			pstm.execute();

			stm.close();
			pstm.close();
			conn.close();
			JOptionPane.showMessageDialog(this, "Thêm vào thành công!");
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Không đúng định dạng!");
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
			ThemDoanhThu();
			capNhatDoanhThu();

		} else if (e.getSource() == datLaiButton) {
			ngayComboBox.setSelectedItem(ngay);
			thangComboBox.setSelectedItem(thang);
			namComboBox.setSelectedItem(nam);
			tienChiTextField.setText("");
			tienThuTextField.setText("");
			
		}
	}

//	public static void main(String[] args) {
//		new PhieuDoanhThu();
//	}
}
