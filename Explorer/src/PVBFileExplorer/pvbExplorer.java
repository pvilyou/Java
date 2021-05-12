package PVBFileExplorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

public class pvbExplorer implements ActionListener {
	
	public static JTree tree;
	public static JTable jtable;
	public static Desktop desktop;
	public static JPopupMenu popupMenu;
	public static DefaultTableModel tableModel;
	public static File copy;
	public static JButton back;
	public static JButton forward;
	public static JTextField pathT;
	public static int iLag = 0;
	public static String fw;
	
	public pvbExplorer() {
		JFrame jf = new JFrame("File Explorer");
		
		jf.getContentPane().setLayout(new BorderLayout());
		jf.setBackground(Color.white);
		
		jtable = new JTable();
		desktop = Desktop.getDesktop();
		
		JPanel pTop = new JPanel();
		pTop.setLayout(new BorderLayout());
		pTop.setPreferredSize(new Dimension(30, 30));
		JPanel pBot = new JPanel();
		pBot.setLayout(new GridBagLayout());
		pBot.setPreferredSize(new Dimension(30, 25));
		
		pathT = new JTextField(60);
		pBot.add(pathT);
		pTop.add(pBot, BorderLayout.SOUTH);
		
		back = new JButton("<");
		back.setPreferredSize(new Dimension(45, 20));
		back.addActionListener(this);
		pBot.add(back);
		
		forward = new JButton(">");
		forward.setPreferredSize(new Dimension(45, 20));
		forward.addActionListener(this);
		pBot.add(forward);

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
		JMenuItem renItem = new JMenuItem("Rename");
		popupMenu.add(renItem);
		popupMenu.addSeparator();
		JMenuItem editItem = new JMenuItem("Edit");
		popupMenu.add(editItem);
		JMenuItem printItem = new JMenuItem("Print");
		popupMenu.add(printItem);
		JMenuItem refItem = new JMenuItem("Refresh");
		popupMenu.add(refItem);
		JMenuItem newFileItem = new JMenuItem("New File");
		popupMenu.add(newFileItem);
		JMenuItem newFolderItem = new JMenuItem("New Folder");
		popupMenu.add(newFolderItem);
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
				jtable.getColumnModel().getColumn(0).setMaxWidth(30);
//				jtable.getColumnModel().getColumn(2).setMaxWidth(100);
//				jtable.getColumnModel().getColumn(3).setMaxWidth(100);
				jtable.getColumnModel().getColumn(4).setMaxWidth(100);
				
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
							popupMenu.show(e.getComponent(), e.getX(), e.getY());
							
							openItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											desktop.open(f);
											iLag = 1;
											
											File ff = new File(pathT.getText());
											pathT.setText(ff.getAbsolutePath());
											UpdateTable(new DefaultMutableTreeNode(ff));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							copyItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											copy = new File(path);
											iLag = 1;
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							pasteItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
								            File f = new File(pathT.getText());
								            copyFolder(copy, f);
											pathT.setText(f.getAbsolutePath());
											iLag = 1;
											UpdateTable(new DefaultMutableTreeNode(f));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							delItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											f.delete();
											iLag = 1;

											File ff = new File(pathT.getText());
											pathT.setText(ff.getAbsolutePath());
											UpdateTable(new DefaultMutableTreeNode(ff));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							renItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											
											String k = JOptionPane.showInputDialog("Nhập tên mới");
											String rename = path.substring(0, path.lastIndexOf(File.separator))+ "\\" +k;
											File ff = new File(rename);
											f.renameTo(ff);
											iLag = 1;

											File fff = new File(pathT.getText());
											pathT.setText(fff.getAbsolutePath());
											UpdateTable(new DefaultMutableTreeNode(fff));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							editItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										while (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											desktop.edit(f);
											iLag = 1;
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							printItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										while (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											desktop.print(f);
											iLag = 1;
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							refItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									
									File ff = new File(pathT.getText());
									pathT.setText(ff.getAbsolutePath());
									UpdateTable(new DefaultMutableTreeNode(ff));
								}
							});
							iLag = 0;
							newFileItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											
											String k = JOptionPane.showInputDialog("Nhập tên cho file mới");
											String rename = path.substring(0, path.lastIndexOf(File.separator))+ "\\" +k;
											File f = new File(rename);
											f.createNewFile();
											iLag = 1;
											
											File ff = new File(pathT.getText());
											pathT.setText(ff.getAbsolutePath());
											UpdateTable(new DefaultMutableTreeNode(ff));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							newFolderItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											
											String k = JOptionPane.showInputDialog("Nhập tên cho folder mới");
											String rename = path.substring(0, path.lastIndexOf(File.separator))+ "\\" +k;
											File f = new File(rename);
											f.mkdir();
											iLag = 1;
											
											File ff = new File(pathT.getText());
											pathT.setText(ff.getAbsolutePath());
											UpdateTable(new DefaultMutableTreeNode(ff));
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
							proItem.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									try {
										if (iLag == 0) {
											JTable target = (JTable) e.getSource();
											int row = target.getSelectedRow();
											String path = jtable.getValueAt(row, 5).toString();
											File f = new File(path);
											desktop.open(f);
											iLag = 1;
										}
									} catch (Throwable t) {}
								}
							});
							iLag = 0;
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			fw = pathT.getText();
			File f = new File(fw.substring(0, fw.lastIndexOf(File.separator)));
			pathT.setText(f.getAbsolutePath());
			UpdateTable(new DefaultMutableTreeNode(f));
		}
		else if (e.getSource() == forward) {
			File f = new File(fw);
			pathT.setText(f.getAbsolutePath());
			UpdateTable(new DefaultMutableTreeNode(f));
		}
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
				int rowCount = pvbExplorer.tableModel.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					pvbExplorer.tableModel.removeRow(i);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a");
                for (File child : chunks) {
                    pvbExplorer.tableModel.addRow( new Object[] {
        				FileSystemView.getFileSystemView().getSystemIcon(child), 
        				FileSystemView.getFileSystemView().getSystemDisplayName(child), 
        				sdf.format(child.lastModified()) , 
        				FileSystemView.getFileSystemView().getSystemTypeDescription(child),
        				(int)(Math.ceil((double) child.length()/(1024)))+" KB", 
        				child.getAbsoluteFile()
            		});
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
	
	private static void copyFolder(File sourceFolder, File targetFolder) throws IOException {
        if (sourceFolder.isDirectory()) {
            if (!targetFolder.exists()) {
                targetFolder.mkdir();
            }
            String files[] = sourceFolder.list();
            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File tarFile = new File(targetFolder, file);
                copyFolder(srcFile, tarFile);
            }
        } else {
            Files.copy(sourceFolder.toPath(), targetFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
	
	public static void main(String[] args) {
		new pvbExplorer();
	}
}