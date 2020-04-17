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
	public List<ArticleModel> findAll() {
		return articleDAO.findAll();
	}

}
