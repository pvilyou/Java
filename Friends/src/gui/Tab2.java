package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tab2 extends JPanel {
	JTextField jtfUserName, jtfPwd;

	public Tab2() {
		setLayout(new GridLayout(3,2));
		
		JLabel jlbUserName = new JLabel("Java");
		add(jlbUserName);
		
		jtfUserName = new JTextField(20);
		add(jtfUserName);

		JLabel jlbPwd = new JLabel("C++");
		add(jlbPwd);
		
		jtfPwd = new JTextField(20);
		add(jtfPwd);
		
		JButton jbtLogin = new JButton("Add");
	//	jbtLogin.addActionListener(this);
		add(jbtLogin);
		
		JButton jbtCancel = new JButton("Search");
		add(jbtCancel);
	}
}
