import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
public class DemoPopupMenu extends JFrame {
	
	JPopupMenu popupMenu;
	JMenuItem saveItem, cutItem, copyItem, pasteItem,helpItem;
//	JTextArea textArea;
	
	public DemoPopupMenu() {
//		textArea = new JTextArea("Click the mouse right button inside \nthe frame.");
//		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//		getContentPane().add(textArea);
		
		popupMenu = new JPopupMenu("Popup Menu Explorer");
		saveItem = new JMenuItem("Save");
		popupMenu.add(saveItem);
		popupMenu.addSeparator();
		cutItem = new JMenuItem("Cut");
		popupMenu.add(cutItem);
		copyItem = new JMenuItem("Copy");
		popupMenu.add(copyItem);
		pasteItem = new JMenuItem("Paste");
		popupMenu.add(pasteItem);
		popupMenu.addSeparator();
		helpItem = new JMenuItem("Help");
		popupMenu.add(helpItem);

		PopupMenuListener pml = new PopupMenuListener();
		addMouseListener(pml);
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
				popupMenu.show(me.getComponent(),
				me.getX(), me.getY());
			}
		}
	}

	public static void main(String[] args) {
		DemoPopupMenu frame = new DemoPopupMenu();
		frame.setTitle("Demo PopupMenu");
		frame.setSize(700, 800);
		frame.setVisible(true);
	}
}