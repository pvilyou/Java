package Bai_001;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ExSwing_004_TextField_TextArea_TextEtc extends JFrame implements ActionListener {

	public ExSwing_004_TextField_TextArea_TextEtc() {
		JButton continueButton = new JButton("Continue");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		continueButton.setVisible(true);
		continueButton.setLayout(new FlowLayout());
		
		//this.se
		//continueButton.setLabel(null);
		
		
		
	}
	
	public static void main(String[] args) {
		new ExSwing_004_TextField_TextArea_TextEtc();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
