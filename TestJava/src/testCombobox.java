import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class testCombobox extends JFrame implements ActionListener {
	
	private Container c;
	private JButton bt;
	private JComboBox ngay;
	private JComboBox thang;
	private JComboBox<Integer> nam;
	
	private int currentDay;
	private int currentMonth;
	private int currentYear;
	
	public testCombobox() {
		setTitle("Test combobox");
		setSize(430, 320);
		setLocationRelativeTo(null);

		c = getContentPane();
		c.setLayout(null);
		
//		String ngay[] = { "Ha Noi", "Vinh Phuc", "Da Nang", 
//                "TP. Ho Chi Minh", "Nha Trang" };
// 
		//int ng[] = {1,2,3};
		// cb = new JComboBox(ngay);
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		currentDay = cal.get(Calendar.DATE);
		currentMonth = cal.get(Calendar.MONTH)+1;
		currentYear = cal.get(Calendar.YEAR);
		
		SimpleDateFormat datefm = new SimpleDateFormat("yyyy-MM-dd");
		//String stringDate = textfel.getText();
		try {
			Date datet = datefm.parse("");
				//System.err.println();
			final java.sql.Date sqlDate = convert(datet);
			System.err.println(sqlDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
        
//		ngay = new JComboBox(ngayk);
//		ngay.setBounds(150, 50, 150, 20);
//		c.add(ngay);
//		
//		thang = new JComboBox(thang);
//		thang.setBounds(150, 50, 150, 20);
//		c.add(thang);
//		
//        nam = new JComboBox(nam);
//        nam.setBounds(150, 50, 150, 20);
//        c.add(nam);
        
        nam = new JComboBox<Integer>();
        nam.setBounds(200, 50, 150, 20);
        c.add(nam);
		
		for (int i = 1; i <= currentMonth; i++) { nam.addItem(i);  }
		
		
		
		
		
//		
		bt = new JButton("ok");
		bt.setBounds(100,100,100,100);
		c.add(bt);
		bt.addActionListener(this);
		setVisible(true);
		
	}
	
	private java.sql.Date convert(Date datet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			
			JOptionPane.showMessageDialog(this, currentDay);
			JOptionPane.showMessageDialog(this, currentMonth);
			JOptionPane.showMessageDialog(this, currentYear);
			JOptionPane.showMessageDialog(this, nam.getSelectedItem().toString());
		}
	}
	//cb.getSelectedItem().toString()
	
	public static void main(String[] args) {
		new testCombobox();
	}
	
}
