package fileexplorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class copy {
	
	public static JTree tree;
	public static JPopupMenu popupMenu;
	public static DefaultTableModel tableModel;
	public static JTable jtable = new JTable();

	public copy() {
		JFrame jf = new JFrame("Explorer");

		jf.getContentPane().setLayout(new BorderLayout());
		jf.setBackground(Color.white);

		JPanel pTop = new JPanel();
		pTop.setLayout(new BorderLayout());
		pTop.setPreferredSize(new Dimension(30, 30));
		JPanel pBot = new JPanel();
		pBot.setLayout(new GridBagLayout());
		pBot.setPreferredSize(new Dimension(30, 28));
		JTextField pathT = new JTextField();
		pathT.setColumns(80);

		pBot.add(pathT);
		pTop.add(pBot, BorderLayout.SOUTH);

		JPanel pLeft = new JPanel();
		pLeft.setLayout(new GridLayout());
		pLeft.setBorder(new EmptyBorder(2, 5, 0, 0));
		pLeft.setPreferredSize(new Dimension(230, 150));
		pLeft.setBackground(Color.white);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		DefaultTreeModel treeModel = new DefaultTreeModel(root);

		popupMenu = new JPopupMenu();
		JMenuItem openItem = new JMenuItem("Open");
		popupMenu.add(openItem);
		popupMenu.addSeparator();
		JMenuItem copyItem = new JMenuItem("Copy");
		popupMenu.add(copyItem);
		JMenuItem pasteItem = new JMenuItem("Paste");
		popupMenu.add(pasteItem);
		JMenuItem delItem = new JMenuItem("Delete");
		popupMenu.add(delItem);
		JMenuItem refItem = new JMenuItem("Refresh");
		popupMenu.add(refItem);
		JMenuItem reItem = new JMenuItem("Rename");
		popupMenu.add(reItem);
		popupMenu.addSeparator();
		JMenuItem proItem = new JMenuItem("Properties");
		popupMenu.add(proItem);
		
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};

			@Override
			public Class<? extends Object> getColumnClass(int column) {
				if (getRowCount() > 0) {
					Object value = getValueAt(0, column);
					if (value != null) {
						return getValueAt(0, column).getClass();
					}
				}
				return super.getColumnClass(column);
			}
		};
		
		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				String[] colName = { "Icon", "Name", "Date modified", "Type", "Size", "Path/name" };
				tableModel.setColumnIdentifiers(colName);
				jtable.setModel(tableModel);
				jtable.getTableHeader().setReorderingAllowed(false);
				showChildren(node);
				UpdateTable(node);
				pathT.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.getColumnModel().getColumn(0).setMaxWidth(30);
				
				jtable.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
							JTable target = (JTable) e.getSource();
							int row = target.getSelectedRow();
							String path = jtable.getValueAt(row, 5).toString();
							File f = new File(path);

							pathT.setText(f.getAbsolutePath());
							UpdateTable(new DefaultMutableTreeNode(f));
						}
						if (e.getButton() == MouseEvent.BUTTON3) {
							int r = jtable.rowAtPoint(e.getPoint());
							if (r >= 0 && r < jtable.getRowCount()) {
								jtable.setRowSelectionInterval(r, r);
							} else {
								jtable.clearSelection();
							}
							int rowi = jtable.getSelectedRow();
							if (rowi < 0)
								return;
							popupMenu.show(e.getComponent(), e.getX(), e.getY());
							
							refItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									for (int i = 0; i < tableModel.getRowCount(); i++) {
										tableModel.removeRow(i);
									}
									UpdateTable(node);
								}
							});
						}
					}
				});
				pathT.setText(node.toString());
			}
		};
		File[] roots = FileSystemView.getFileSystemView().getRoots();
		for (File fileSystemRoot : roots) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
			root.add(node);
			File[] files = FileSystemView.getFileSystemView().getFiles(fileSystemRoot, true);
			for (File file : files) {
				if (file.isDirectory()) {
					node.add(new DefaultMutableTreeNode(file));
				}
			}
		}
		tree = new JTree(treeModel);
		tree.setRootVisible(false);
		tree.setCellRenderer(new Tree());

		tree.expandRow(0);
		JScrollPane treeScroll = new JScrollPane(tree);
		pLeft.add(treeScroll);

		JPanel pRight = new JPanel();
		pRight.setLayout(new GridLayout());
		pRight.setPreferredSize(new Dimension(100, 100));
		pRight.setBackground(Color.white);

		JScrollPane sP1 = new JScrollPane(jtable);
		sP1.getViewport().setBackground(Color.white);
		pRight.add(sP1);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable.setAutoCreateRowSorter(true);
		jtable.setShowHorizontalLines(false);
		jtable.setShowVerticalLines(true);
		jtable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jtable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jtable.setGridColor(Color.LIGHT_GRAY);
		jtable.setRowHeight(23);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);

		sp.setDividerSize(4);
		jf.getContentPane().add(pTop, BorderLayout.NORTH);
		jf.getContentPane().add(sp, BorderLayout.CENTER);

		tree.addTreeSelectionListener(treeSelectionListener);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1000, 600);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	
	public void UpdateTable(DefaultMutableTreeNode node) {
		SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
			@Override
			public Void doInBackground() {
				File file = (File) node.getUserObject();
				File[] files = FileSystemView.getFileSystemView().getFiles(file, true);
				for (File child: files) {
					publish(child);
				}
				return null;
			}
			
			@Override
	            protected void process(List<File> chunks) {
					int rowCount = copy.tableModel.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						copy.tableModel.removeRow(i);
					}
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a");
	                for (File child : chunks) {
	                    copy.tableModel.addRow(new Object[] {FileSystemView.getFileSystemView().getSystemIcon(child), FileSystemView.getFileSystemView().getSystemDisplayName(child), sdf.format(child.lastModified()) , FileSystemView.getFileSystemView().getSystemTypeDescription(child),(int)(Math.ceil((double) child.length()/(1024)))+" KB", child.getAbsoluteFile()});
	                }
	            }
		};
		worker.execute();
	}

	private void showChildren(final DefaultMutableTreeNode node) {
		tree.setEnabled(false);

		SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
			@Override
			public Void doInBackground() {
				File file = (File) node.getUserObject();
				if (file.isDirectory()) {
					File[] files = FileSystemView.getFileSystemView().getFiles(file, true);
					if (node.isLeaf()) {
						for (File child : files) {
							if (child.isDirectory()) {
								publish(child);
							}
						}
					}
				}
				return null;
			}
			@Override
			protected void process(List<File> chunks) {
				for (File child : chunks) {
					node.add(new DefaultMutableTreeNode(child));
				}
			}
			@Override
			protected void done() {
				tree.setEnabled(true);
			}
		};
		worker.execute();
	}
	

	public static void main(String[] args) {
		new copy();
	}
}