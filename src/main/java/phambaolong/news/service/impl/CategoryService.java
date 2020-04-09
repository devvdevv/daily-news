package phambaolong.news.service.impl;

import java.util.List;

import javax.inject.Inject;

import phambaolong.news.dao.ICategoryDAO;
import phambaolong.news.model.CategoryModel;
import phambaolong.news.service.ICategoryService;

public class CategoryService implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}
}
