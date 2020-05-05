package phambaolong.news.service;

import java.util.List;

import phambaolong.news.model.CommentModel;

public interface ICommentService {
	CommentModel save(CommentModel comment);
	CommentModel findOne(Long id);
	List<CommentModel> findByArticle (Long articleId);
}
