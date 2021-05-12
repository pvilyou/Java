package Bai_001;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExSwing_003_CreateButtonAndEvent extends JFrame implements ActionListener {

	public ExSwing_003_CreateButtonAndEvent() {
		JButton okButton = new JButton("OK");			// tạo một cái nút OK
		JButton exitButton = new JButton("Exit");		// tạo một cái nút Exit
		JButton cancelButton = new JButton("Cancel");	// tạo một cái nút Cancel
		
		setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.add(okButton);
		okButton.setActionCommand("ok");	// tạo một cái tên nhẹ nhàng cho cái nút đó
		okButton.addActionListener(this);	// tạo sự kiện
		
		this.add(exitButton);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(this);
		exitButton.setToolTipText("Click vào là thoát chương trình đó con");	// hiện một cái dòng khi đưa chuột vào button
		
		this.add(cancelButton);
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);

		this.setLayout(new FlowLayout());	// cho nó hiển thị phía trên cùng ở giữa
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("ok".equals(e.getActionCommand()))	// nếu nhấn ok thì đồng nghĩa (equals) e nhận lệnh hành động
			JOptionPane.showMessageDialog(rootPane, "OK luôn");	// hiện hộp tuỳ ch�?n(OptionPane): hiển thị hộp thoại tin nhắn
		if("cancel".equals(e.getActionCommand()))
			JOptionPane.showConfirmDialog(rootPane, "Mày có chắc không ?");	// hiển thị tuỳ ch�?n: hiển thị hộp thoại xác nhận
		if("exit".equals(e.getActionCommand()))
			System.exit(0);	// thoát
	}
	
	public static void main(String[] args) {
		new ExSwing_003_CreateButtonAndEvent();
	}


}
