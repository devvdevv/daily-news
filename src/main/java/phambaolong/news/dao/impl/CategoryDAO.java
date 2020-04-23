package phambaolong.news.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phambaolong.news.dao.ICategoryDAO;
import phambaolong.news.model.CategoryModel;

public class CategoryDAO implements ICategoryDAO {

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
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();
			while (result.next()) {
				CategoryModel category = new CategoryModel();
				category.setId(result.getLong("id"));
				category.setName(result.getString("name"));
				category.setCode(result.getString("code"));
				category.setShortDescription(result.getString("shortdescription"));
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		CategoryModel category = new CategoryModel();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, code);
			result = statement.executeQuery();
			while (result.next()) {
				category.setId(result.getLong("id"));
				category.setName(result.getString("name"));
				category.setCode(result.getString("code"));
				category.setShortDescription(result.getString("shortdescription"));
			}
			return category;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
