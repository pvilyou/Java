package Bai_001;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ExSwing_002_Extends extends JFrame {
	
	public ExSwing_002_Extends() {
		JButton jb = new JButton("Click me ne");
		jb.setBounds(100, 100, 100, 40);
		
		add(jb);
		
		setLayout(null);
		setSize(300, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ExSwing_002_Extends();
	}
}
