package Exp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class ExplorerGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	public static JTable table = new JTable();
	public static DefaultTableModel tableModel = new DefaultTableModel();
	public static JTextField txtPath;
	public static String currentFolder = "";
	public static File currentFile;
	public static JPopupMenu popupMenu;
	public static Desktop desktop;
	private JMenuItem openItem, cutItem, copyItem, pasteItem, helpItem;
	
	private JPanel gui;
	
	private JTree tree;
	private DefaultTreeModel treeModel;
	

	public ExplorerGUI() {
		MainTable();
	}

	public void MainTable() {

		desktop = Desktop.getDesktop();
		
		setTitle("Explorer by Phan Van Bang");
		getContentPane().setLayout(new BorderLayout());
		// setBackground(Color.white);

		JPanel pTop = new JPanel();
		pTop.setLayout(new BorderLayout());
		pTop.setPreferredSize(new Dimension(30, 30));
		JPanel pBot = new JPanel();
		pBot.setPreferredSize(new Dimension(30, 28));

		JTextField pathT = new JTextField();
		pathT.setColumns(50);
		pBot.add(pathT);
		pTop.add(pBot, BorderLayout.SOUTH);

		JPanel pLeft = new JPanel();
		pLeft.setLayout(new GridLayout());
		pLeft.setPreferredSize(new Dimension(240, 100));
		pLeft.setBackground(Color.white);

		
		
		
		
		File[] fileRoot = File.listRoots();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("This PC");
		
		
		
		
		
		for (File index : fileRoot) {
			String strS = index.getPath().replace("\\", "");
			DefaultMutableTreeNode driver = new DefaultMutableTreeNode(strS);
			ChildNode ccn = new ChildNode(index, driver);
			new Thread(ccn).start();
			root.add(driver);
		}

		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		JTree jtree = new JTree(treeModel);
		jtree.setCellRenderer(new DefaultTreeCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean bSelected,
					boolean bExpanded, boolean bLeaf, int iRow, boolean bHasFocus) {
				Component c = super.getTreeCellRendererComponent(tree, value, bSelected, bExpanded, false, iRow,
						bHasFocus);
				return c;
			}
		});
		jtree.setShowsRootHandles(true);
		JScrollPane sP = new JScrollPane(jtree);
		pLeft.add(sP);

		JPanel pRight = new JPanel();
		pRight.setLayout(new GridLayout());
		pRight.setPreferredSize(new Dimension(100, 100));
		pRight.setBackground(Color.white);

		JScrollPane sP1 = new JScrollPane(table);
		sP1.getViewport().setBackground(Color.white);
		pRight.add(sP1);
		table.setShowGrid(false);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(Color.white);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);

		sp.setDividerSize(3);
		getContentPane().add(pTop, BorderLayout.NORTH);
		getContentPane().add(sp, BorderLayout.CENTER);

		
		
		popupMenu = new JPopupMenu("Popup Menu Explorer");
		openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					System.out.println(": " + currentFolder);
					desktop.open(currentFile);
				} catch (Throwable t) {
				}
			}
		});
//			openItem.addActionListener((ActionListener) this);
		popupMenu.add(openItem);
		popupMenu.addSeparator();
		cutItem = new JMenuItem("Cut");
//			cutItem.addActionListener((ActionListener) this);
		popupMenu.add(cutItem);
		copyItem = new JMenuItem("Copy");
//			copyItem.addActionListener((ActionListener) this);
		popupMenu.add(copyItem);
		pasteItem = new JMenuItem("Paste");
//			pasteItem.addActionListener((ActionListener) this);
		popupMenu.add(pasteItem);
		popupMenu.addSeparator();
		helpItem = new JMenuItem("Help");
//			helpItem.addActionListener((ActionListener) this);
		popupMenu.add(helpItem);
		
		jtree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String[] colName = { "Name", "Date modified", "Type", "Size" };
				tableModel.setColumnIdentifiers(colName);
				table.setModel(tableModel);

				String strpath = "";
				javax.swing.tree.TreePath treepath = e.getPath();
				Object[] ele = treepath.getPath();
				for (int i = 0, n = ele.length; i < n; i++) {
					strpath += ele[i] + "\\";
				}
				pathT.setText(strpath.replace("This PC\\", ""));
				strpath = pathT.getText();
				UpdateGUI(strpath);

			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
				String name = table.getModel().getValueAt(row, 0).toString();

				if (e.getClickCount() == 2) {
					currentFolder = pathT.getText() + "\\" + name;
					String strpath = "";

					File file = new File(currentFolder);
					if (file.isDirectory()) {
						try {
							System.out.println("Locate: " + currentFile.getParentFile());
							desktop.open(currentFile.getParentFile());
						} catch (Throwable t) {
							showThrowable(t);
						}
						pathT.setText(currentFolder);
						strpath = pathT.getText();
						UpdateGUI(strpath);
					}
				}
				

				PopupMenuListener pml = new PopupMenuListener();
				table.addMouseListener(pml);
			}

			private void showThrowable(Throwable t) {
				// TODO Auto-generated method stub
				
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	class PopupMenuListener extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			showPopup(me);
		}

		public void mouseReleased(MouseEvent me) {
			showPopup(me);
		}

		private void showPopup(MouseEvent me) {
			if (me.isPopupTrigger()) {
				popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		}
	}

	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand().equals("Open")) {
//			System.err.println("sdnjkfdsbfkjsn");
//		}
//	}

	public void UpdateGUI(String path) {
		ExplorerGUI.tableModel = (DefaultTableModel) ExplorerGUI.table.getModel();
		int rowCount = ExplorerGUI.tableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			ExplorerGUI.tableModel.removeRow(i);
		}
		ExplorerGUI.tableModel = (DefaultTableModel) ExplorerGUI.table.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a");
		File file = new File(path);
		File[] list = file.listFiles();
		
		for (File files : list) {
			String name = files.getName();
			String type = "";
			if (files.isFile()) {
				type = "." + name.substring(name.lastIndexOf(".") + 1);
			}
			if (files.isDirectory()) {
				type = "Folder";
			}
			if (files.isHidden()==false) {
				if (files.isDirectory()) {
					ExplorerGUI.tableModel.addRow(new Object[] { name, sdf.format(files.lastModified()), type, ""});
				} else {
					ExplorerGUI.tableModel.addRow(new Object[] { name, sdf.format(files.lastModified()), type,
					(int) (Math.ceil((double) files.length() / (1024))) + " KB" });
				}
			}
		}
	}
	
	public class ChildNode implements Runnable {

		private DefaultMutableTreeNode root;
		private File fileRoot;

		public ChildNode(File fileRoot, DefaultMutableTreeNode root) {
			this.fileRoot = fileRoot;
			this.root = root;
		}

		@Override
		public void run() {
			createChildren(fileRoot, root);
		}

		public void createChildren(File fileRoot, DefaultMutableTreeNode root) {
			File[] files = fileRoot.listFiles();
			if (files != null) {
				for (File child : files) {
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new Nodes(child));
					if (child.isDirectory() && child.isHidden() == false) {
						root.add(childNode);
						createChildren(child, childNode);
					}
				}
			}
		}
	}
	
	public class Nodes {

		private File file;

		public Nodes(File file) {
			this.file = file;
		}

		public File getFile() {
			return file;
		}

		@Override
		public String toString() {
			String name = file.getName();
			if (name.equals("")) {
				return file.getAbsolutePath();
			} else {
				return name;
			}
		}
	}
	
	
	

	public static void main(String[] args) {
		new ExplorerGUI();
	}
}
