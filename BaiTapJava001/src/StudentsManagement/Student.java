package StudentsManagement;

public class Student {
	private String id;
	private String name;
	private int NoCourse;
	private Course[] list;

	public Student(String id, String name, int NoCourse, Course[] list) {
		this.id = id;
		this.name = name;
		this.NoCourse = NoCourse;
		this.list = list;
	}

	public float getAvgScore() {
		float score = 0;
		int cresum = 0;
		for (int i = 0; i < this.NoCourse; i++) {
			score += this.list[i].getNote() * this.list[i].getCredit();
			cresum += this.list[i].getCredit();
		}
		return score / cresum;
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getNoCourse() {
		return this.NoCourse;
	}

	public Course[] getList() {
		return this.list;
	}

}
