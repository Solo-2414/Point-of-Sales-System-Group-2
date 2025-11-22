package panels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.dbConnection;
import helpers.buttonHelper;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

public class productPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public productPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 1009, 557);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnManageProd = new JButton("MANAGE PRODUCT");
		btnManageProd.setFont(new Font("Poppins", Font.PLAIN, 13));	
		btnManageProd.setBounds(806, 0, 162, 42);
		buttonHelper.apply(btnManageProd, "", 1, 1);
		btnManageProd.setForeground(Color.BLACK);
		add(btnManageProd);
		
		loadProducts(); 
	}
	private void loadProducts() {
	    try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "SELECT * FROM products";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        DefaultTableModel model = new DefaultTableModel(
	            new Object[]{"ID","Name","Description","Price","Reorder Level","Stock"}, 
	            0
	        );

	        while (rs.next()) {
	            model.addRow(new Object[]{
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getDouble("price"),
	                rs.getInt("reorder_level"),
	                rs.getInt("stock")
	            });
	        }

	        table.setModel(model);
	        table.setRowHeight(30);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
