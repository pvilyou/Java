import javax.swing.JOptionPane;

public class testJOP {

	public testJOP() {
		String timKiem = JOptionPane.showInputDialog("Nhập thông tin bạn cần Tìm Kiếm:", "");
		System.err.println(timKiem.endsWith("Cancel"));
	}
	
	
	
	public static void main(String[] args) {
		new testJOP();
	}
}
