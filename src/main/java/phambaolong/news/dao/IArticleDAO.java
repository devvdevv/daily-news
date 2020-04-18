package phambaolong.news.dao;

import java.util.List;

import phambaolong.news.model.ArticleModel;

public interface IArticleDAO {
	Long save(ArticleModel newArticle);
	ArticleModel findOne(Long id);
	void update(ArticleModel updatedArticle);
	void delete(Long id);
	List<ArticleModel> findAll(Integer limit, Integer offset, String sortBy); 
	Integer countAll();
}
