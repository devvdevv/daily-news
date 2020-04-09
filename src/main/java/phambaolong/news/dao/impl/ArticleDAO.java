package phambaolong.news.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import phambaolong.news.dao.IArticleDAO;
import phambaolong.news.model.ArticleModel;

public class ArticleDAO implements IArticleDAO {
	
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
	public Long save(ArticleModel newArticle) {
		Long id = null;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO article(title, thumbnail, content, user_id, category_id,");
		sql.append("createdby, createddate ) VALUES( ?, ?, ?, ?, ?, ?, ?)");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newArticle.getTitle());
			statement.setString(2, newArticle.getThumbnail());
			statement.setString(3, newArticle.getContent());
			statement.setLong(4, newArticle.getUserId());
			statement.setLong(5, newArticle.getCatogoryId());
			statement.setString(6, newArticle.getCreatedBy());
			statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			while(result.next()) {
				id = result.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (result != null)
					result.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
