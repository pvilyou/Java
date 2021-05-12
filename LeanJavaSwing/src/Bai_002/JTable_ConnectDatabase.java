package Bai_002;

public class JTable_ConnectDatabase {
//	Vector vData = null, vTitle = null;
//	try {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection conn = (Connection) DriverManager
//				.getConnection("jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
//		Statement stm = (Statement) conn.createStatement();
//		ResultSet rst = stm.executeQuery("SELECT * FROM doanh_thu");
//
//		ResultSetMetaData rstmeta = (ResultSetMetaData) rst.getMetaData();
//		int num_column = rstmeta.getColumnCount();
//
//		vTitle = new Vector(num_column);
//		for (int i = 1; i <= num_column; i++)
//			vTitle.add(rstmeta.getColumnLabel(i));
//
//		vData = new Vector(10, 10);
//		while (rst.next()) {
//			Vector row = new Vector(num_column);
//			for (int i = 1; i <= num_column; i++)
//				row.add(rst.getString(i));
//			vData.add(row);
//		}
//		rst.close();
//		stm.close();
//		conn.close();
//
//	} catch (Exception e) {
//		System.out.println(e.getMessage());
//	}
//
//	JScrollPane tableResult = new JScrollPane(new JTable(vData, vTitle));
//	p2.add(tableResult);
//	// setContentPane(tableResult);
//	show();
	
	
	
	
	
	
	
	
//
//	Object duLieuHang[][] = { { "", "", "", "", "", "" } };
//	Object tenCot[] = { "ID", "Ngày Chi", "Khoản Chi", "Số Lượng", "Đơn Giá", "Thành Tiền" };
//
//	mTableModel = new DefaultTableModel(duLieuHang, tenCot);
//	table = new JTable(mTableModel);
//
//	try {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection conn = (Connection) DriverManager.getConnection(
//				"jdbc:mysql://localhost:3306/doanh_thu_ve_so?useSSL=false", "root", "Pvb171717ml");
//
//		Statement stmt = (Statement) conn.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM phieu_chi");
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		add(scrollPane, BorderLayout.CENTER);
//		setVisible(true);
//
//		mTableModel.removeRow(0);
//		Object[] rows;
//		while (rs.next()) {
//			rows = new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), 
//								  rs.getString(4), rs.getString(5), rs.getString(6)  };
//			mTableModel.addRow(rows);
//		}
//		stmt.close();
//		rs.close();
//		conn.close();
//
//	} catch (Exception e1) {
//		System.err.println(e1.getMessage());
//	}

}
