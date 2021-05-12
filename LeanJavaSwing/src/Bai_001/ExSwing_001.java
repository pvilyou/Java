package Bai_001;

import javax.swing.JButton;		// cai nut
import javax.swing.JFrame;		// khung

public class ExSwing_001 {

	public ExSwing_001() {
		JFrame jf = new JFrame();				// tao the hien cho Jframe (khung)
		jf.setTitle("Vail luon dau cat moi");	// tieu de
		// or JFrame jf = new JFrame("Vail luon dau cat moi");
		
		JButton jb = new JButton("Tui ne");		// tao the hien cho JButton (cai nut)
		jb.setBounds(100, 100, 80, 30);			// cai dat vi tri cho cai nut
		
		jf.add(jb);								// them cai nut vao khung
		
		jf.setSize(300, 300);					// kich thuoc khung voi width (rong) = 300 va height (dai) = 300 
		jf.setLayout(null);						// khong bo tri
		jf.setVisible(true);					// hien thi khung
		jf.setLocationRelativeTo(null);			// cho cai hinh hien o giua man hinh
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);	// tat cua so thi dung chuong trinh
	}
		
	public static void main(String[] args) {
		new ExSwing_001();
	}

}
