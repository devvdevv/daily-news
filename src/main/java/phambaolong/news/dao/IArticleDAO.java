package phambaolong.news.dao;

import phambaolong.news.model.ArticleModel;

public interface IArticleDAO {
	Long save(ArticleModel newArticle);
	ArticleModel findOne(Long id);
	void update(ArticleModel updatedArticle);
	void delete(Long id);
}
