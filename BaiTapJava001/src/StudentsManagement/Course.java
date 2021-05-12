package StudentsManagement;

public class Course {
	private String id;
	private String title;
	private int credit;
	private float note;

	public Course(String id, String title, int credit, float note) {
		this.id = id;
		this.title = title;
		this.credit = credit;
		this.note = note;
	}

	public String getIDC() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public int getCredit() {
		return this.credit;
	}

	public float getNote() {
		return this.note;
	}

}
