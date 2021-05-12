package BaiTapThucHanhMau;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Bai3 extends JFrame{

	private static final long serialVersionUID = 1L;
	private HighThread high;
	private LowThread low;
	
	private TextArea output;
	public Bai3() {
		super ("Bai3");
		output = new TextArea(10,20);
		add(output);
		
		setSize(250,200);
		setVisible(true);
		
		high = new HighThread(output);
		high.start();
		
		low = new LowThread(output);
		low.start();
	}
	
	
	public static void main(String[] args) {
		Bai4 app = new Bai4();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

class HighThread extends Thread {
	private TextArea display;
	public HighThread(TextArea a) {
		display = a;
		setPriority(Thread.MAX_PRIORITY);
	}

	public void run() {
		for(int x=1; x<=5; x++) {
			display.append("High Priority Thread!\n");
		}
	}
}

class LowThread extends Thread{
	private TextArea display;
	public LowThread(TextArea a) {
		display = a;
		setPriority(Thread.MAX_PRIORITY);
	}

	public void run() {
		for(int y=1; y<=5; y++) {
			display.append("Low Priority Thread!\n");
		}
	}
}

