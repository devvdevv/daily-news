package phambaolong.news.service.impl;

import java.util.List;

import javax.inject.Inject;

import phambaolong.news.dao.IArticleDAO;
import phambaolong.news.model.ArticleModel;
import phambaolong.news.service.IArticleService;

public class ArticleService implements IArticleService {
	
	@Inject
	private IArticleDAO articleDAO;
	
	@Override
	public ArticleModel save(ArticleModel newArticle) {
		Long id = articleDAO.save(newArticle);
		return articleDAO.findOne(id);
	}

	@Override
	public ArticleModel findOne(Long id) {
		return articleDAO.findOne(id);
	}

	@Override
	public ArticleModel update(ArticleModel updatedArticle) {
		articleDAO.update(updatedArticle);
		return articleDAO.findOne(updatedArticle.getId());
	}

	@Override
	public void delete(Long[] listId) {
		for(Long id : listId) {
			// article has a relationship with comment (article-id)
			// so check does it have any comment belong.
			// if yes => delete them first then delete article.
			articleDAO.delete(id);
		}
	}

	@Override
	public List<ArticleModel> findAll(Integer limit, Integer offset, String sortBy) {
		return articleDAO.findAll(limit, offset, sortBy);
	}

	@Override
	public Integer countAll() {
		return articleDAO.countAll();
	}

	@Override
	public List<ArticleModel> findByCategoryId(Long categoryId) {
		return articleDAO.findByCategoryId(categoryId);
	}

	@Override
	public List<ArticleModel> findByUser(Long id) {
		return articleDAO.findByUser(id);
	}

}
