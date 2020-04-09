package phambaolong.news.controller.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phambaolong.news.model.ArticleModel;
import phambaolong.news.model.CategoryModel;
import phambaolong.news.service.IArticleService;
import phambaolong.news.service.ICategoryService;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IArticleService articleService;

	private static final long serialVersionUID = -8064909454214279743L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		List<CategoryModel> categories = categoryService.findAll();
//		request.setAttribute("categories", categories);
		String title = "Ban luan ve JDBC";
		String thumbnail = "";
		String content = "JDBC co gi vui ?";
		Long userId = 2L;
		Long categoryId = 1L;
		String createdBy = "user1";
		ArticleModel article = new ArticleModel();
		article.setTitle(title);
		article.setThumbnail(thumbnail);
		article.setContent(content);
		article.setUserId(userId);
		article.setCatogoryId(categoryId);
		article.setCreatedBy(createdBy);
		Long id = articleService.save(article);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(request, response);
	}

}
