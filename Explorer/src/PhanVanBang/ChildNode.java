package PhanVanBang;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

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
