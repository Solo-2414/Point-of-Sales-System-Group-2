package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.dbConnection;
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

}
