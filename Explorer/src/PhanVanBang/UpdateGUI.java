package PhanVanBang;

import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class UpdateGUI {

	public UpdateGUI(String path) {
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
}
