package Bai_001_ClassesObjects;

public class Cat {
	private String name;
	private int age;
	private String color;
	
	public Cat() {
		
	}
	
	public Cat(String name) {
		this.name = name;
	}
	
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Cat(String name, int age, String color) {
		this(name, age);	// co the lam nhu the nay vi o tren da co this.name va this.age roi nha 
		this.color = color;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}

