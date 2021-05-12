package Bai_001_ClassesObjects;

import java.util.Scanner;

public class ClassesObjects {
	
	public static void main(String[] args) {
		try (Scanner ss = new Scanner(System.in)) {
			Cat catCute01 = new Cat("Tom");
			System.out.println("Cat's name: " + catCute01.getName());

			catCute01.setAge(ss.nextInt());
			System.out.println("Cat's age: " + catCute01.getAge());
		}
		
	}

}
