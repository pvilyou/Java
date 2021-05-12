package Form;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangKy extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private Container c;			// getContentPane()
	private JLabel TieuDeLabel;
	private JLabel TenDaiLyLabel;
	private JTextField TenDaiLyTextField;
	private JLabel DiaChiLabel;
	private JTextField DiaChiTextField;
	private JLabel DienThoaiLabel;
	private JTextField DienThoaiTextField;
	private JLabel VungMienLabel;
	private JComboBox<String> VungMienComboBox;
	private JLabel TenDangNhapLabel;
	private JTextField TenDangNhapTextField;
	private JLabel MatKhauLabel;
	private JPasswordField MatKhauPasswordField;
	private JButton DangKyButton;
	private JLabel ThongBaoLabel;
	private JButton DatLaiButton;
	
	private String vungMien[] = { "Miá»�n Báº¯c", "Miá»�n Trung", "Miá»�n Nam" };

	public DangKy() {
		setTitle("Ä�Ä‚NG KĂ� THĂ”NG TIN TĂ€I KHOáº¢N");
		setSize(430, 430);
		setLocationRelativeTo(null);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		c = getContentPane();
		c.setLayout(null);
		
		TieuDeLabel = new JLabel("Ä�Äƒng kĂ½");
		TieuDeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		TieuDeLabel.setBounds(165, 5, 300, 60);
		c.add(TieuDeLabel);
		
		TenDaiLyLabel = new JLabel("TĂªn Ä‘áº¡i lĂ½:");
		TenDaiLyLabel.setBounds(43, 75, 99, 14);
		c.add(TenDaiLyLabel);
		
		TenDaiLyTextField = new JTextField();
		TenDaiLyTextField.setBounds(130, 72, 250, 25);
		c.add(TenDaiLyTextField);
		TenDaiLyTextField.setColumns(10);

		DiaChiLabel = new JLabel("Ä�á»‹a chá»‰:");
		DiaChiLabel.setBounds(58, 113, 99, 14);
		c.add(DiaChiLabel);
		
		DiaChiTextField = new JTextField();
		DiaChiTextField.setBounds(130, 110, 250, 25);
		c.add(DiaChiTextField);
		DiaChiTextField.setColumns(10);
		
		DienThoaiLabel = new JLabel("Ä�iá»‡n thoáº¡i:");
		DienThoaiLabel.setBounds(40, 151, 99, 14);
		c.add(DienThoaiLabel);
		
		DienThoaiTextField = new JTextField();
		DienThoaiTextField.setBounds(130, 148, 250, 25);
		c.add(DienThoaiTextField);
		DienThoaiTextField.setColumns(10);
		
		VungMienLabel = new JLabel("BĂ¡n VĂ© Miá»�n:");
		VungMienLabel.setBounds(26, 189, 99, 14);
		c.add(VungMienLabel);
		
		VungMienComboBox = new JComboBox(vungMien);
		VungMienComboBox.setBounds(130, 186, 99, 25);
		c.add(VungMienComboBox);
		
		TenDangNhapLabel = new JLabel("TĂªn Ä‘Äƒng nháº­p:");
		TenDangNhapLabel.setBounds(13, 227, 99, 14);
		c.add(TenDangNhapLabel);
		
		TenDangNhapTextField = new JTextField();
		TenDangNhapTextField.setBounds(130, 227, 250, 25);
		c.add(TenDangNhapTextField);
		TenDangNhapTextField.setColumns(10);
	
		MatKhauLabel = new JLabel("Máº­t kháº©u:");
		MatKhauLabel.setBounds(46, 265, 99, 14);
		c.add(MatKhauLabel);
		
		MatKhauPasswordField = new JPasswordField();
		MatKhauPasswordField.setBounds(130, 265, 250, 25);
		c.add(MatKhauPasswordField);
		MatKhauPasswordField.setColumns(10);
		
		DangKyButton = new JButton("Ä�Äƒng kĂ½");
		DangKyButton.setBounds(130, 315, 80, 25);
		DangKyButton.addActionListener(this);
		c.add(DangKyButton);
		
		ThongBaoLabel = new JLabel("");
		ThongBaoLabel.setBounds(130, 344, 200, 25);
		c.add(ThongBaoLabel);

		DatLaiButton = new JButton("Ä�áº·t laá»‹");
		DatLaiButton.setBounds(225, 315, 80, 25);
		DatLaiButton.addActionListener(this);
		c.add(DatLaiButton);

		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DangKyButton) {
//			setTenDaiLy(TenDaiLyTextField.getText());
//			setDiaChi(DiaChiTextField.getText());
//			setSoDienThoai(DienThoaiTextField.getText());
//			//d.setVungMien(VungMienComboBox.);
//			setTenDangNhap(TenDangNhapTextField.getText());
//			setMatKhau(MatKhauPasswordField.getText());
//			ThongBaoLabel.setText("Ä�Äƒng kĂ½ thĂ nh cĂ´ng!");
			
		} 
		else if (e.getSource() == DatLaiButton) {
			TenDaiLyTextField.setText("");
			DiaChiTextField.setText("");
			DienThoaiTextField.setText("");
			VungMienComboBox.setSelectedItem(null);
			TenDangNhapTextField.setText("");
			MatKhauPasswordField.setText("");
			ThongBaoLabel.setText("");
		}	
	}

	public static void main(String[] args) throws Exception {
		new DangKy();
	}
}
