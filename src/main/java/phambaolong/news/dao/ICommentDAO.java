package phambaolong.news.dao;

import java.util.List;

import phambaolong.news.model.CommentModel;

public interface ICommentDAO {
	Long save(CommentModel comment);
	CommentModel findOne(Long id);
	List<CommentModel> findByArticle(Long articleId);
}
