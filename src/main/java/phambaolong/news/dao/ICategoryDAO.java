package phambaolong.news.dao;

import java.util.List;

import phambaolong.news.model.CategoryModel;

public interface ICategoryDAO {
	List<CategoryModel> findAll();
}
