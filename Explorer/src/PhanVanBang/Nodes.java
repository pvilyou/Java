package PhanVanBang;

import java.io.File;

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
