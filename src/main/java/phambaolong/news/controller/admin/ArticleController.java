package phambaolong.news.controller.admin;

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
import phambaolong.news.utils.FormUtil;
import phambaolong.news.utils.MessageUtil;
import phambaolong.news.utils.PagingUtil;

@WebServlet(urlPatterns = { "/admin-article" })
public class ArticleController extends HttpServlet {

	@Inject
	private IArticleService articleService;
	
	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 8444055740588402089L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleModel model = FormUtil.toModel(request, ArticleModel.class);
		String view = "";
		if (model.getType().equals("list")) {
			model.setTotalItems(articleService.countAll());
			model.setTotalPages(PagingUtil.getTotalPages(model.getTotalItems(), model.getItemsOnPage()));

			Integer limit = model.getItemsOnPage();
			Integer offset = (model.getPage() - 1) * limit;
			model.setListItems(articleService.findAll(limit, offset, model.getSortBy()));
			String message = request.getParameter("message");
			request.setAttribute("message", MessageUtil.getMessage(message));
			view = "/views/admin/article/list-article.jsp";
		} else if (model.getType().equals("edit")) {
			if (model.getId() != null) {
				model = articleService.findOne(model.getId());
			}
			List<CategoryModel> categories = categoryService.findAll();
			request.setAttribute("categories", categories);
			view = "/common/article/edit.jsp";
		}
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
