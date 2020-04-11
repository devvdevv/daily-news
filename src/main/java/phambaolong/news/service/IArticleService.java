package phambaolong.news.service;

import phambaolong.news.model.ArticleModel;

public interface IArticleService {
	ArticleModel save(ArticleModel newArticle);
	ArticleModel findOne(Long id);
	ArticleModel update(ArticleModel updatedArticle);
	void delete(Long[] listId);
}
