package phambaolong.news.service;

import java.util.List;

import phambaolong.news.model.ArticleModel;

public interface IArticleService {
	ArticleModel save(ArticleModel newArticle);
	ArticleModel findOne(Long id);
	ArticleModel update(ArticleModel updatedArticle);
	void delete(Long[] listId);
	List<ArticleModel> findAll(Integer limit, Integer offset, String sortBy);
	Integer countAll();
}
