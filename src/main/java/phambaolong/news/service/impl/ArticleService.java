package phambaolong.news.service.impl;

import javax.inject.Inject;

import phambaolong.news.dao.IArticleDAO;
import phambaolong.news.model.ArticleModel;
import phambaolong.news.service.IArticleService;

public class ArticleService implements IArticleService {
	
	@Inject
	private IArticleDAO articleDAO;
	
	@Override
	public Long save(ArticleModel newArticle) {
		return articleDAO.save(newArticle);
	}

}
