package BaiTapThucHanhMau;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Bai4 extends JFrame{

	private static final long serialVersionUID = 1L;
	private HighThread1 high;
	private LowThread1 low;
	
	private TextArea output;
	public Bai4() {
		super ("Bai4");
		output = new TextArea(10,20);
		add(output);
		
		setSize(250,200);
		setVisible(true);
		
		high = new HighThread1(output);
		high.start();
		
		low = new LowThread1(output);
		low.start();
	}
	
	
	public static void main(String[] args) {
		Bai3 app = new Bai3();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

class HighThread1 extends Thread {
	private TextArea display;
	public HighThread1(TextArea a) {
		display = a;
		setPriority(Thread.MAX_PRIORITY);
	}

	public void run() {
		for(int x=1; x<=5; x++) {
			display.append("High Priority Thread!\n");
			////
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class LowThread1 extends Thread{
	private TextArea display;
	public LowThread1(TextArea a) {
		display = a;
		setPriority(Thread.MAX_PRIORITY);
	}

	public void run() {
		for(int y=1; y<=5; y++) {
			display.append("Low Priority Thread!\n");
		}
	}
}

