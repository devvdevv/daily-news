package phambaolong.news.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import phambaolong.news.dao.ICommentDAO;
import phambaolong.news.model.CommentModel;

public class CommentDAO implements ICommentDAO {
	
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
	public Long save(CommentModel comment) {
		Long id = null;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO comment(content, user_id, article_id, createdby, createddate)");
		sql.append(" VALUES(?, ?, ?, ?, ?)");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, comment.getContent());
			statement.setLong(2, comment.getUserId());
			statement.setLong(3, comment.getArticleId());
			statement.setString(4, comment.getCreatedBy());
			statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			while (result.next()) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public CommentModel findOne(Long id) {
		String sql = "SELECT * FROM comment WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		CommentModel comment = new CommentModel();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			result = statement.executeQuery();
			while(result.next()) {
				comment.setId(result.getLong("id"));
				comment.setContent(result.getString("content"));
				comment.setUserId(result.getLong("user_id"));
				comment.setArticleId(result.getLong("article_id"));
				comment.setCreatedBy(result.getString("createdby"));
				comment.setCreatedDate(result.getTimestamp("createddate"));
			}
			return comment;
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

	@Override
	public List<CommentModel> findByArticle(Long articleId) {
		String sql = "SELECT * FROM comment WHERE article_id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<CommentModel> comments = new ArrayList<CommentModel>();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, articleId);
			result = statement.executeQuery();
			while(result.next()) {
				CommentModel comment = new CommentModel();
				comment.setId(result.getLong("id"));
				comment.setContent(result.getString("content"));
				comment.setUserId(result.getLong("user_id"));
				comment.setArticleId(result.getLong("article_id"));
				comment.setCreatedBy(result.getString("createdby"));
				comment.setCreatedDate(result.getTimestamp("createddate"));
				comments.add(comment);
			}
			return comments;
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

	@Override
	public void deleteByArticleId(Long articleId) {
		String sql = "DELETE FROM comment WHERE article_id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, articleId);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
