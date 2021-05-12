package Bai_001;

import java.awt.Button;
import java.awt.Frame;

public class ExAWT_002 extends Frame{

	public ExAWT_002() {
		Frame f = new Frame();	// tao the hien cua Frame (khung)
		f.setTitle("Vail luon dau cat moi :v");
		
		Button b = new Button("Click me");
		b.setBounds(100, 130, 80, 30);
		
		f.add(b);
		
		f.setSize(300, 300);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ExAWT_002();
	}

}
