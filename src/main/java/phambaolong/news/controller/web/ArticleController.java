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
import phambaolong.news.model.UserModel;
import phambaolong.news.service.IArticleService;
import phambaolong.news.service.ICategoryService;
import phambaolong.news.service.ICommentService;
import phambaolong.news.utils.FormUtil;
import phambaolong.news.utils.MessageUtil;
import phambaolong.news.utils.SessionUtil;

@WebServlet (urlPatterns = {"/article"})
public class ArticleController extends HttpServlet{
	
	@Inject
	private IArticleService articleService;

	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private ICommentService commentService;
	
	private static final long serialVersionUID = -1580614207126965275L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleModel model = FormUtil.toModel(request, ArticleModel.class);
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		String view = "";
		if (model.getType().equals("detail")) {
			if (model.getId() != null) {
				model = articleService.findOne(model.getId());
				if (model != null) {
					model.setListComment(commentService.findByArticle(model.getId()));
					view = "/views/web/article/detail.jsp";
				} else {
					view = "/common/error/404.jsp";
				}
				request.setAttribute("model", model);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		} else if (model.getType().equals("edit")) {
			if (user != null) {
				if (model.getId() != null) {
					model = articleService.findOne(model.getId());
				}
				List<CategoryModel> categories = categoryService.findAll();
				request.setAttribute("categories", categories);
				view = "/common/article/edit.jsp";	
				request.setAttribute("model", model);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/enter?action=login&message=login_needed");
			}
		} else if (model.getType().equals("list")) {
			if (model.getCategoryId() != null && model.getCategoryId() == 0) {
				if (user != null) {
					model.setListItems(articleService.findByUser(user.getId()));
					view = "/views/web/article/list.jsp";
				}
			} else if (model.getCategoryId() != null && model.getCategoryId() > 0) {
				model.setListItems(articleService.findByCategoryId(model.getCategoryId()));
				view = "/views/web/home.jsp";
			}
			String message = request.getParameter("message");
			List<CategoryModel> categories = categoryService.findAll();
			request.setAttribute("model", model);
			request.setAttribute("categories", categories);
			request.setAttribute("message", MessageUtil.getMessage(message));
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
