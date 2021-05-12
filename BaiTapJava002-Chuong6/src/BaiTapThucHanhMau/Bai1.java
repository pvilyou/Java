package BaiTapThucHanhMau;

class Thread_1 extends Thread {
	public void run() {
		for(int i=1; i<10; i+=2) {
			System.out.print(i+" ");
		}
	}
}

class Thread_2 extends Thread {
	public void run() {
		for(int i=2; i<=10; i+=2) {
			System.out.print(i+" ");
		}
	}
}

public class Bai1 {
	
	public static void main(String[] args) {
		Thread t1 = new Thread_1();
		Thread t2 = new Thread_2();
		
		t1.start();
		t2.start();
	}
	
}
