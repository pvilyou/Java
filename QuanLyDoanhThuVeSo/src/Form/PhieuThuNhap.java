package Form;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class PhieuThuNhap extends JFrame implements ActionListener {

	private Container c;
	private JLabel TieuDeLabel;
	private JLabel NgayThuLabel;
	private JTextField NgayThuTextField;
	private JLabel KhoanThuLabel;
	private JTextField KhoanThuTextField;
	private JLabel SoLuongLabel;
	private JTextField SoLuongTextField;
	private JLabel DonGiaLabel;
	private JTextField DonGiaTextField;
	private JButton ThemButton;
	private JLabel ThongBaoLabel;
	private JButton DatLaiButton;

	public PhieuThuNhap() {
		setTitle("TẠO PHIẾU THU");
		setSize(430, 360);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		c = getContentPane();
		c.setLayout(null);

		TieuDeLabel = new JLabel("Phiếu Thu");
		TieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		TieuDeLabel.setBounds(160, 5, 300, 60);
		c.add(TieuDeLabel);

		NgayThuLabel = new JLabel("Ngày Thu:");
		NgayThuLabel.setBounds(43, 75, 99, 20);
		c.add(NgayThuLabel);

		NgayThuTextField = new JTextField();
		NgayThuTextField.setBounds(130, 75, 230, 25);
		c.add(NgayThuTextField);
		NgayThuTextField.setColumns(10);

		KhoanThuLabel = new JLabel("Khoản Thu:");
		KhoanThuLabel.setBounds(34, 113, 99, 20);
		c.add(KhoanThuLabel);

		KhoanThuTextField = new JTextField();
		KhoanThuTextField.setBounds(130, 113, 230, 25);
		c.add(KhoanThuTextField);
		KhoanThuTextField.setColumns(10);

		SoLuongLabel = new JLabel("Số Lượng:");
		SoLuongLabel.setBounds(35, 151, 99, 20);
		c.add(SoLuongLabel);

		SoLuongTextField = new JTextField();
		SoLuongTextField.setBounds(130, 151, 230, 25);
		c.add(SoLuongTextField);
		SoLuongTextField.setColumns(10);

		DonGiaLabel = new JLabel("Đơn Giá:");
		DonGiaLabel.setBounds(46, 189, 99, 20);
		c.add(DonGiaLabel);

		DonGiaTextField = new JTextField();
		DonGiaTextField.setBounds(130, 189, 230, 25);
		c.add(DonGiaTextField);
		DonGiaTextField.setColumns(10);

		ThemButton = new JButton("Thêm vào");
		ThemButton.setBounds(105, 244, 90, 25);
		ThemButton.addActionListener(this);
		c.add(ThemButton);

		ThongBaoLabel = new JLabel("");
		ThongBaoLabel.setBounds(105, 275, 200, 25);
		c.add(ThongBaoLabel);

		DatLaiButton = new JButton("Đặt laị");
		DatLaiButton.setBounds(222, 244, 90, 25);
		DatLaiButton.addActionListener(this);
		c.add(DatLaiButton);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ThemButton) {
			ThongBaoLabel.setText("Thêm thu nhập thành công!");
			
			Vector vData = null, vTitle = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
				Statement stm = (Statement) conn.createStatement();
				PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(
						"insert into doanh_thu_ve_so.phieu_thu(Ngày_Thu, Khoản_Thu, Số_Lượng, Đơn_Giá, Tiền_Thu) "
								+ "value (?,?,?,?,Số_Lượng*Đơn_Giá);");

				pstm.setString(1, NgayThuTextField.getText());
				pstm.setString(2, KhoanThuTextField.getText());
				pstm.setString(3, SoLuongTextField.getText());
				pstm.setString(4, DonGiaTextField.getText());
				pstm.execute();

				stm.close();
				pstm.close();
				conn.close();

			} catch (Exception e1) {
				ThongBaoLabel.setText("Không đúng định dạng!");
				System.out.println(e1.getMessage());
			}

		} else if (e.getSource() == DatLaiButton) {
			NgayThuTextField.setText("");
			KhoanThuTextField.setText("");
			SoLuongTextField.setText("");
			DonGiaTextField.setText("");
			ThongBaoLabel.setText("");
		}
	}
	
	
	public static void main(String[] args) {
		new PhieuThuNhap();
	}

}
