package phambaolong.news.service.impl;

import java.util.List;

import javax.inject.Inject;

import phambaolong.news.dao.ICommentDAO;
import phambaolong.news.model.CommentModel;
import phambaolong.news.service.ICommentService;

public class CommentService implements ICommentService {
	
	@Inject
	private ICommentDAO commentDAO;

	@Override
	public CommentModel save(CommentModel comment) {
		Long id = commentDAO.save(comment);		
		return commentDAO.findOne(id);
	}

	@Override
	public CommentModel findOne(Long id) {
		return commentDAO.findOne(id);
	}

	@Override
	public List<CommentModel> findByArticle(Long articleId) {
		return commentDAO.findByArticle(articleId);
	}

}
