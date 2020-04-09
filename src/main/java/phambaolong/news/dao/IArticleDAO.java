package phambaolong.news.dao;

import phambaolong.news.model.ArticleModel;

public interface IArticleDAO {
	Long save(ArticleModel newArticle);
}
