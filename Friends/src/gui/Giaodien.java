package gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connection.DBConnection;

public class Giaodien extends JFrame implements ActionListener {
	DefaultTableModel dm;
	JTable tbl;
	JTextField jtf1, jtf2, jtf3; 
	DBConnection dbCon;
	JPanel p2; 
	public Giaodien() {
		setLayout(new GridLayout(2,1));
		JMenuBar mnBar = new JMenuBar();
		JMenu view = new JMenu("View");
		
		JMenuItem students = new JMenuItem("Table");
		students.addActionListener(this);
		JMenuItem teachers = new JMenuItem("Import");
		teachers.addActionListener(this);
		
		view.add(students);
		view.add(teachers);
		mnBar.add(view);
		setJMenuBar(mnBar);
		
		JPanel p1 = new JPanel();
		Border border1 = BorderFactory.createLineBorder(Color.red);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(border1, "Panel1"); 
		p1.setBorder(titledBorder1);
		p1.setLayout(new BorderLayout());
		
		//p11
		JPanel p11 = new JPanel();
		//Border border11 = BorderFactory.createLineBorder(Color.red);
		//TitledBorder titledBorder11 = BorderFactory.createTitledBorder(border11, "Panel11"); 
		//p11.setBorder(titledBorder11);
		p1.add(p11, BorderLayout.WEST);
		p11.setLayout(new GridLayout(3,1));
		
		JLabel jlb1 = new JLabel("Họ và tên");
		p11.add(jlb1);
		
		JLabel jlb2 = new JLabel("Quê quán");
		p11.add(jlb2);
		
		JLabel jlb3 = new JLabel("Lớp");
		p11.add(jlb3);
		
		//p12
		JPanel p12 = new JPanel();
		//Border border12 = BorderFactory.createLineBorder(Color.red);
		//TitledBorder titledBorder12 = BorderFactory.createTitledBorder(border12, "Panel12"); 
		//p12.setBorder(titledBorder12);
		p1.add(p12, BorderLayout.CENTER);
		
		p12.setLayout(new GridLayout(3,1));
		
		jtf1 = new JTextField(20);
		p12.add(jtf1);
		
		jtf2 = new JTextField(20);
		p12.add(jtf2);
		
		jtf3 = new JTextField(20);
		p12.add(jtf3);
		//p13
		JPanel p13 = new JPanel();
		//Border border13 = BorderFactory.createLineBorder(Color.red);
		//TitledBorder titledBorder13 = BorderFactory.createTitledBorder(border13, "Panel13"); 
		//p13.setBorder(titledBorder13);
		p1.add(p13, BorderLayout.EAST);
		
		p13.setLayout(new GridLayout(3,1));
		JButton jbt1 = new JButton("Add");
		p13.add(jbt1);
		
		jbt1.addActionListener(this);
		
		
		
		
		
		
		
		p2 = new JPanel();
		Border border2 = BorderFactory.createLineBorder(Color.blue);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(border2, "Panel2"); 
		p2.setBorder(titledBorder2);
		
		p2.setLayout(new CardLayout());
		
		dm=new DefaultTableModel();
		dm.addColumn("Há»� vĂ  tĂªn");
		dm.addColumn("QuĂª quĂ¡n");
		dm.addColumn("Lá»›p");
		
		tbl=new JTable(dm);
		//dm.addRow(new String []{"NgÄ‚Â´ vĂ„Æ’n BĂ¡ÂºÂ¯p", "QuĂ¡ÂºÂ£ng Nam"});
		
//		dbCon = new DBConnection();
//		
//		try {
//			ResultSet rs = dbCon.getStmt().executeQuery("select * from student");
//			
//			//them du lieu vao csdl cho cac bang
//			while (rs.next()) {
//				
//				dm.addRow(new String []{rs.getString(2), rs.getString(4), rs.getString(5)});
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
		
		JScrollPane sc=new JScrollPane(tbl);
		add(p1);
		
		p2.add(sc, "table");
		p2.add(new Tab2(), "pane2");
		
		add(p2);
		
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nameButton = e.getActionCommand();//e.getSource()
		if(nameButton.equals("Add")){
			//System.out.println("Click add");
			//System.out.println(jtf1.getText());
			dm.addRow(new String []{jtf1.getText(),jtf2.getText(), jtf3.getText()});
			
			try {
				dbCon.getStmt().execute("insert into student(hoTen, queQuan, idLop) values (\""+jtf1.getText()+"\",\""+jtf2.getText()+"\",\""+jtf3.getText()+"\")");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (nameButton.equals("Table")) {
			System.out.println("Click Cancel");
			CardLayout cl=(CardLayout)p2.getLayout();
			cl.show(p2, "table");
		}else if (nameButton.equals("Import")) {
			System.out.println("Click Teachers");
			CardLayout cl=(CardLayout)p2.getLayout();
			cl.show(p2, "pane2");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Giaodien();
		
	}
	

}
