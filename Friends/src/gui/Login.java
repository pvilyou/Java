package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	JTextField jtfUserName, jtfPwd;
	
	public Login() {
		setSize(350, 150);
		
		setLayout(new GridLayout(3,2));
		
		JLabel jlbUserName = new JLabel("UserName");
		add(jlbUserName);
		
		jtfUserName = new JTextField(20);
		add(jtfUserName);

		JLabel jlbPwd = new JLabel("Password");
		add(jlbPwd);
		
		jtfPwd = new JTextField(20);
		add(jtfPwd);
		
		JButton jbtLogin = new JButton("Login");
		jbtLogin.addActionListener(this);
		add(jbtLogin);
		
		JButton jbtCancel = new JButton("Cancel");
		add(jbtCancel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nameButton = e.getActionCommand();//e.getSource()
		if(nameButton.equals("Login")){
			if(jtfUserName.getText().equals("hnt") && jtfPwd.getText().equals("123")) {
				this.setVisible(false);
				Giaodien x = new Giaodien();
			}
		}
	}

	public static void main(String args[]) {
		new Login();
	}

	
}
