package Bai_001;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class DangNhap extends JFrame implements ActionListener {

	private Container c;
	private JLabel TieuDeLabel;
	private JLabel TenDangNhapLabel;
	private JTextField TenDangNhapTextField;
	private JLabel MatKhauLabel;
	private JPasswordField MatKhauTextField;
	private JCheckBox NhoMatKhau;
	private JButton DangNhapButton;
	private JLabel ThongBaoLabel;
	private JButton DangKyButton;

	public DangNhap() {
		setTitle("ĐĂNG NHẬP VÀO TÀI KHOẢN");
		setSize(380, 285);
		setLocationRelativeTo(null);

		c = getContentPane();
		c.setLayout(null);

		TieuDeLabel = new JLabel("Đăng Nhập");
		TieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		TieuDeLabel.setBounds(125, 5, 300, 60);
		c.add(TieuDeLabel);

		TenDangNhapLabel = new JLabel("Tên Tài Khoản:");
		TenDangNhapLabel.setBounds(25, 75, 99, 14);
		c.add(TenDangNhapLabel);

		TenDangNhapTextField = new JTextField();
		TenDangNhapTextField.setBounds(130, 72, 200, 25);
		c.add(TenDangNhapTextField);
		TenDangNhapTextField.setColumns(10);

		MatKhauLabel = new JLabel("Mật Khẩu:");
		MatKhauLabel.setBounds(57, 113, 99, 14);
		c.add(MatKhauLabel);

		MatKhauTextField = new JPasswordField();
		MatKhauTextField.setBounds(130, 110, 200, 25);
		c.add(MatKhauTextField);

		NhoMatKhau = new JCheckBox("Ghi nhớ mật khẩu ?");
		NhoMatKhau.setBounds(54, 143, 200, 25);
		c.add(NhoMatKhau);

		ThongBaoLabel = new JLabel("");
		ThongBaoLabel.setBounds(75, 210, 270, 25);
		c.add(ThongBaoLabel);
		
		DangNhapButton = new JButton("Đăng Nhập");
		DangNhapButton.setBounds(75, 183, 95, 25);
		DangNhapButton.addActionListener(this);
		c.add(DangNhapButton);

		DangKyButton = new JButton("Đăng ký ?");
		DangKyButton.setBounds(200, 183, 95, 25);
		DangKyButton.addActionListener(this);
		c.add(DangKyButton);

		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DangNhapButton) {
			if (TenDangNhapTextField.getText().equalsIgnoreCase("admin")
					&& MatKhauTextField.getText().equals("12345")) {
				if (NhoMatKhau.isSelected()) {
					ThongBaoLabel.setText("Đăng nhập và ghi nhớ tài khoản thành công");
				} else {
					ThongBaoLabel.setText("Đăng nhập thành công");
				}

				
			} else {
				ThongBaoLabel.setText("Tài khoản hoặc mật khẩu sai !");
			}
		} 
		else if (e.getSource() == DangKyButton) {
			new DangKy();
		}	
	}
	
	public static void main(String[] args) {
		new DangNhap();
	}
}