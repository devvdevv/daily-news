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
		sql.append("createdby, createddate, shortdescription ) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)");
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
			statement.setLong(5, newArticle.getCategoryId());
			statement.setString(6, newArticle.getCreatedBy());
			statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			statement.setString(8, newArticle.getShortDescription());

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
	public ArticleModel findOne(Long id) {
		String sql = "SELECT * FROM article WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArticleModel article = new ArticleModel();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			result = statement.executeQuery();
			while(result.next()) {
				article.setId(result.getLong("id"));
				article.setTitle(result.getString("title"));
				article.setThumbnail(result.getString("thumbnail"));
				article.setShortDescription(result.getString("shortdescription"));
				article.setContent(result.getString("content"));
				article.setUserId(result.getLong("user_id"));
				article.setCategoryId(result.getLong("category_id"));
				article.setCreatedBy(result.getString("createdby"));
				article.setCreatedDate(result.getTimestamp("createddate"));
			}
			return article;
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
	public void update(ArticleModel updatedArticle) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE article SET title= ?, thumbnail = ?, shortdescription = ?, ");
		sql.append("content = ?, category_id = ?, modifiedby = ?, modifieddate = ? WHERE id = ?");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, updatedArticle.getTitle());
			statement.setString(2, updatedArticle.getThumbnail());
			statement.setString(3, updatedArticle.getShortDescription());
			statement.setString(4, updatedArticle.getContent());
			statement.setLong(5, updatedArticle.getCategoryId());
			statement.setString(6, updatedArticle.getModifiedBy());
			statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			statement.setLong(8, updatedArticle.getId());
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

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM article WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
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

	@Override
	public List<ArticleModel> findAll(Integer limit, Integer offset, String sortBy) {
		StringBuilder sql = new StringBuilder("SELECT * FROM article");
		if (sortBy != null) {
			if (sortBy.equals("Latest"))
				sql.append(" ORDER BY createddate DESC");
			else if (sortBy.equals("Oldest"))
				sql.append(" ORDER BY createddate ASC");
		}
		if (limit != null && offset != null) {
			sql.append(" LIMIT " + limit + " OFFSET " + offset);
		}
		List<ArticleModel> listArticle = new ArrayList<ArticleModel>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			result = statement.executeQuery();
			while (result.next()) {
				ArticleModel article = new ArticleModel();
				article.setId(result.getLong("id"));
				article.setTitle(result.getString("title"));
				article.setThumbnail(result.getString("thumbnail"));
				article.setShortDescription(result.getString("shortdescription"));
				article.setContent(result.getString("content"));
				article.setCategoryId(result.getLong("category_id"));
				article.setUserId(result.getLong("user_id"));
				article.setCreatedBy(result.getString("createdby"));
				article.setCreatedDate(result.getTimestamp("createddate"));
				listArticle.add(article);
			}
			return listArticle;
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
	public Integer countAll() {
		String sql = "SELECT count(*) FROM article";
		Integer counter = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();
			while (result.next()) {
				counter = result.getInt(1);
			}
			return counter;
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
	public List<ArticleModel> findByCategoryId(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM article WHERE category_id = ?");
		List<ArticleModel> listArticle = new ArrayList<ArticleModel>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				ArticleModel article = new ArticleModel();
				article.setId(result.getLong("id"));
				article.setTitle(result.getString("title"));
				article.setThumbnail(result.getString("thumbnail"));
				article.setShortDescription(result.getString("shortdescription"));
				article.setContent(result.getString("content"));
				article.setCategoryId(result.getLong("category_id"));
				article.setUserId(result.getLong("user_id"));
				article.setCreatedBy(result.getString("createdby"));
				article.setCreatedDate(result.getTimestamp("createddate"));
				listArticle.add(article);
			}
			return listArticle;
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
	public List<ArticleModel> findByUser(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM article WHERE user_id = ?");
		List<ArticleModel> listArticle = new ArrayList<ArticleModel>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				ArticleModel article = new ArticleModel();
				article.setId(result.getLong("id"));
				article.setTitle(result.getString("title"));
				article.setThumbnail(result.getString("thumbnail"));
				article.setShortDescription(result.getString("shortdescription"));
				article.setContent(result.getString("content"));
				article.setCategoryId(result.getLong("category_id"));
				article.setUserId(result.getLong("user_id"));
				article.setCreatedBy(result.getString("createdby"));
				article.setCreatedDate(result.getTimestamp("createddate"));
				listArticle.add(article);
			}
			return listArticle;
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
