package BaiTapThucHanhMau;

public class Bai5 {
	FacThread t1 = new FacThread(2);
	SumThread t2 = new SumThread(3);
	SumPowThread t3 = new SumPowThread(2, 1);

	public Bai5() {
		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			
			long S=t1.getResult()+t2.getResult()+t3.getResult();
			System.out.println("\nKet Qua = " +S);
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		new Bai5();
	}
}

class FacThread extends Thread {
	long gt = 1;
	int n;

	public FacThread(int k) {
		n = k;
	}

	public void run() {
		for (int i = 2; i <= n; i++) {
			gt *= i;
		}
		System.out.println("\nF1 = " + gt);
	}

	public long getResult() {
		return gt;
	}
}

class SumThread extends Thread {
	long S = 0;
	int n;

	public SumThread(int k) {
		n = k;
	}

	public void run() {
		for (int i = 1; i <= n; i++) {
			S += i;
		}
		System.out.println("\nF2 = " + S);
	}

	public long getResult() {
		return S;
	}
}

class SumPowThread extends Thread {
	long S = 0;
	int x, n;

	public SumPowThread(int y, int k) {
		x = y;
		n = k;
	}

	public void run() {
		for (int i = 1; i <= n; i++) {
			S += Math.pow(x, i);
		}
		System.out.println("\nF3 = " + S);
	}

	public long getResult() {
		return S;
	}
}
