package Bai_001;

import java.awt.Button;		// cai nut
import java.awt.Frame;		// khung

public class ExAWT_001 extends Frame {	// ke thua 
	
	public ExAWT_001() {
		setTitle("Vi du 1 AWT trong java");		// tieu de 
		Button b = new Button("Click me");		// tao the hien cua Button (cai nut)
		b.setBounds(100, 130, 80, 30);			// cai dat vi tri cua Button (cai nut)
		
		add(b);									// them cai nut vao khung
		
		setSize(300, 300);						// kich thuoc frame voi width (rong) = 300 va height (dai) = 300
		setLayout(null);						// khong trinh quan ly layout (bo tri)
		setVisible(true);						// hien thi frame
	}
	
	public static void main(String[] args) {
		new ExAWT_001();
	}

}
