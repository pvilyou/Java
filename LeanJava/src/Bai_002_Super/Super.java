package Bai_002_Super;

class Message {
	void message() {
		System.out.println("((((((((((((((((((((:");
	}
}

public class Super extends Message {

	public void message() {
		System.out.println(":))))))))))))))))))))");
	}
	
	public void biThuat() {
		message();
		super.message();
	}
	
	public Super() {
		biThuat();
	}
	
	public static void main(String[] args) {
		new Super();
//		Super s = new Super();
//		s.biThuat();
	}
}
