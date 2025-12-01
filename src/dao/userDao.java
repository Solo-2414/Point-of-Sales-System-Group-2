package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.dbConnection;
import helpers.actionEditor;
import helpers.actionRenderer;
import model.user;

public class userDao {

	public user validateLogin(String username, String password) {
		try {
            Connection conn = dbConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	public boolean addUser(user u) {
        try {
            Connection conn = dbConnection.getConnection();
            String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	public boolean updateUser(user u) {
        try {
            Connection conn = dbConnection.getConnection();
            String sql = "UPDATE users SET username = ?, password_hash = ?, role = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	public boolean deleteUser(int id) {
        try {
            Connection conn = dbConnection.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	public void loadUsers(JTable table) {
	    try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "SELECT * FROM users";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        DefaultTableModel model = new DefaultTableModel(
	            new Object[]{"ID", "Username", "Role", "Actions"}, 
	            0
	        ) {
	        	@Override
		        public boolean isCellEditable(int row, int column) {
		            return column == 3; // all cells non-editable
		        }
	        };

	        while (rs.next()) {
	            model.addRow(new Object[]{
	                rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("role"),
	                "" // actions button
	            });
	        }

	        table.setModel(model);
	        
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
	        table.getColumnModel().getColumn(3).setCellRenderer(new actionRenderer("staff"));
			table.getColumnModel().getColumn(3).setCellEditor(new actionEditor("staff"));
			table.setRowHeight(35);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void refreshUserTable(JTable table) {
	    loadUsers(table);

	}
	public user getUserById(int userId) {
		// TODO Auto-generated method stub
		try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "SELECT * FROM users WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            user u = new user();
	            u.setId(rs.getInt("id"));
	            u.setUsername(rs.getString("username"));
	            u.setPassword(rs.getString("password_hash"));
	            u.setRole(rs.getString("role"));
	            return u;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
