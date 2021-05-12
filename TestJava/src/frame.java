import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class frame implements ActionListener {

	private static JComboBox<String> tangGiamComboBox;
	private static JComboBox<String> luaChonComboBox;
	private static String tangGiam1 = "";
	
	
	public static void main(String[] args) {
		JFrame sapXepJFrame = new JFrame();
		sapXepJFrame.setTitle("CHỌN CÁCH SẮP XẾP");
		sapXepJFrame.setSize(300, 200);
		sapXepJFrame.setLocationRelativeTo(null);
		sapXepJFrame.setLayout(null);
		
		JButton sapXepJFrameButton = new JButton("Sắp xếp");
		sapXepJFrameButton.setBounds(100, 100, 80, 25);
		sapXepJFrameButton.addActionListener(null);
		sapXepJFrame.add(sapXepJFrameButton);
		
//		themButton = new JButton("Thêm Vào");
//		themButton.setBounds(105, 244, 90, 25);
//		themButton.addActionListener(this);
//		add(themButton);
		
		String tangGiam[] = { "Tăng Dần", "Giảm Dần" };
		tangGiamComboBox = new JComboBox<>(tangGiam);
		tangGiamComboBox.setBounds(30, 40, 90, 23);
		sapXepJFrame.add(tangGiamComboBox);
		
		String luaChon[] = { "Theo Ngày", "Theo Tiền Chi", "Theo Tiền Thu", "Theo Lợi Nhuận" };
		luaChonComboBox = new JComboBox<>(luaChon);
		luaChonComboBox.setBounds(140, 40, 115, 23);
		sapXepJFrame.add(luaChonComboBox);
		
		sapXepJFrame.setVisible(true);
		
		if (tangGiamComboBox.getSelectedItem() == "Tăng Dần") tangGiam1 = "ASC";
		if (tangGiamComboBox.getSelectedItem() == "Giảm Dần") tangGiam1 = "DESC";
		
		//tangGiam.getSelectedItem().toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Sắp Xếp")) {
			if (e.getActionCommand().equals("Sắp xếp")) {
				if (luaChonComboBox.getSelectedItem().toString() == "Theo Ngày") {
					//truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Ngày " +tangGiam1);
					System.err.println("vail");
					
				} else if (luaChonComboBox.getSelectedItem().toString() == "Theo Tiền Chi") {
					//truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Tiền_Chi " +tangGiam1);
					
				} else if (luaChonComboBox.getSelectedItem().toString() == "Theo Tiền Thu") {
					//truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Tiền_Thu " +tangGiam1);
					
				} else if (luaChonComboBox.getSelectedItem().toString() == "Theo Lợi Nhuận") {
					//truyVanDuLieu("SELECT * FROM doanh_thu ORDER BY Lợi_Nhuận " +tangGiam1);
	
				}
			}
		}
	}
}
