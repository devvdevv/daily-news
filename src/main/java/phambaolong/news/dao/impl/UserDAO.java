package phambaolong.news.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phambaolong.news.dao.IUserDAO;
import phambaolong.news.model.UserModel;

public class UserDAO implements IUserDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/daily_news";
			String user = "root";
			String password = "1234";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserModel findBy_username_password_status(String username, String password, int status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		UserModel user = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, status);
			result = statement.executeQuery();
			while(result.next()) {
				user = new UserModel();
				user.setId(result.getLong("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setStatus(result.getInt("status"));
				user.setRoleId(result.getLong("role_id"));
				user.setCreatedBy(result.getString("createdby"));
				user.setCreatedDate(result.getTimestamp("createddate"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
				if(result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
