package StudentsManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MainStudent {
	ArrayList<Student> st = new ArrayList<Student>();
	Scanner key = new Scanner(System.in);

	public MainStudent() {
		while (true) {
			Mau();
			System.out.print("Nhap 1 chuc nang: ");
			int opt = key.nextInt();
			key.nextLine();
			switch (opt) {
			case 1:
				Input();
				System.out.println("\n");
				break;
			case 2:
				View();
				System.out.println("\n");
				break;
			case 3:
				SortAVG();
				View();
				System.out.println("\n");
				break;
			case 4:
				FindName();
				System.out.println("\n");
				break;
			case 5:
				FindNameOfHP();
				System.out.println("\n");
				break;
			case 6:
				SortSumCre();
				View();
				System.out.println("\n");
				break;
			case 7:
				FindMaxScore();
				System.out.println("\n");
				break;
			case 8:
				System.out.println("<< DA THOAT CHUONG TRINH >>");
				return;
			default:
				System.out.println("Hay chon lai chuc nang (1-8) \n");
			}
		}
	}

	static void Mau() {
		System.out.println("---MENU CAC CHUC NANG---");
		System.out.println("1. Nhap danh sach sinh vien");
		System.out.println("2. Xem danh sach sien vien");
		System.out.println("3. Sap xep sinh vien tang dan theo diem trung binh");
		System.out.println("4. Tim kiem sinh vien theo ten");
		System.out.println("5. Hien thi tat ca sinh vien hoc cung mot hoc phan");
		System.out.println("6. Sap xep va hien thi sinh vien giam dan theo so luong tin chi");
		System.out.println("7. Tim kiem va hien thi tat ca sinh vien co diem cao nhat cua mot hoc phan");
		System.out.println("8. Thoat");
		System.out.println("------------------------");
	}
	
	// 1
	public void Input() {
		System.out.print("Nhap so luong sinh vien: ");
		int n = key.nextInt();
		key.nextLine();
		Student stn[] = new Student[n];
		for (int i = 0; i < n; i++) {
			System.out.println("Nhap sinh vien thu " + (i + 1));
			System.out.print("ID: ");
			String id = key.nextLine();
			System.out.print("Ten: ");
			String name = key.nextLine();
			System.out.print("So hoc phan: ");
			int hp = Integer.parseInt(key.nextLine());
			Course List[] = new Course[hp];
			for (int j = 0; j < hp; j++) {
				System.out.println("Hoc phan " + (j + 1));
				System.out.print("\tID: ");
				String CourseID = key.nextLine();
				System.out.print("\tTen hoc phan: ");
				String title = key.nextLine();
				System.out.print("\tSo tin chi: ");
				int credit = Integer.parseInt(key.nextLine());
				System.out.print("\tDiem: ");
				float note = Float.parseFloat(key.nextLine());
				List[j] = new Course(CourseID, title, credit, note);
			}
			stn[i] = new Student(id, name, hp, List);
			st.add(stn[i]);
		}
	}
	// 2
	public void View() {
		for (Student s : st) {
			System.out.println("ID: " + s.getID() + ", Ten: " + s.getName() + ", Diem trung binh = " + s.getAvgScore());
		}
	}
	// 3
	public void SortAVG() {
		Collections.sort(st, new Comparator<Student>() {
			public int compare(Student st1, Student st2) {
				Float a = Float.valueOf(st1.getAvgScore());
				Float b = Float.valueOf(st2.getAvgScore());
				return a.compareTo(b);
			}
		});
	}
	// 4
	public void FindName() {
		System.out.print("Nhap ten sinh vien can tim: ");
		String name = key.nextLine();
		for (Student s : st) {
			if (s.getName().indexOf(name) != -1)
				System.out.println("ID: " + s.getID() + ", Ten: " + s.getName() + ", Diem trung binh = " + s.getAvgScore());
		}
	}
	// 5
	public void FindNameOfHP() {
		System.out.print("Nhap ten hoc phan: ");
		String nameHp = key.nextLine();
		for (Student s : st) {
			for (int i = 0; i < s.getNoCourse(); i++) {
				if (s.getList()[i].getTitle().indexOf(nameHp) != -1)
					System.out.println("ID: " + s.getID() + ", Ten: " + s.getName() + ", Diem trung binh = " + s.getAvgScore());
			}
		}
	}
	// 6
	public int SumCre(Student a) {
		int sumcre = 0;
		for (int i = 0; i < a.getNoCourse(); i++) {
			sumcre += a.getList()[i].getCredit();
		}
		return sumcre;
	}

	public void SortSumCre() {
		Collections.sort(st, new Comparator<Student>() {
			public int compare(Student st1, Student st2) {
				Integer a = Integer.valueOf(SumCre(st1));
				Integer b = Integer.valueOf(SumCre(st2));
				return b.compareTo(a);
			}
		});
	}
	// 7
	public void FindMaxScore() {
		System.out.print("Nhap ten hoc phan: ");
		String nameHp = key.nextLine();
		float score = 0;
		for (Student s : st) {
			for (int i = 0; i < s.getNoCourse(); i++) {
				if ((s.getList()[i].getTitle().equals(nameHp)) && (score <= s.getList()[i].getNote())) {
					System.out.println("ID: " + s.getID() + ", Ten: " + s.getName() + ", Diem trung binh = " + s.getAvgScore());
				}
			}
		}
	}

	public static void main(String[] args) {
		new MainStudent();
	}

}
