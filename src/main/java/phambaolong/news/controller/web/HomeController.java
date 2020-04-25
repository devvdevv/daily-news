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
import phambaolong.news.service.IUserService;
import phambaolong.news.utils.FormUtil;
import phambaolong.news.utils.MessageUtil;
import phambaolong.news.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home", "/enter", "/escape" })
public class HomeController extends HttpServlet {

	@Inject
	private IUserService userService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IArticleService articleService;

	private static final long serialVersionUID = -8064909454214279743L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String message = request.getParameter("message");
		if (action != null && action.equals("login")) {
			if (message != null) {
				request.setAttribute("message", MessageUtil.getMessage(message));
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/enter?action=login");
		} else {
			SessionUtil.getInstance().getValue(request, "USERMODEL");
			List<CategoryModel> categories = categoryService.findAll();
			ArticleModel model = new ArticleModel();
			model.setListItems(articleService.findAll(null, null, null));
			request.setAttribute("categories", categories);
			request.setAttribute("model", model);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = FormUtil.toModel(request, UserModel.class);
		model = userService.findBy_username_password_status(model.getUsername(), model.getPassword(), 1);
		if (model != null && model.getRole().getCode() != null) {
			SessionUtil.getInstance().putValue(request, "USERMODEL", model);
			if (model.getRole().getCode().equals("admin")) {
				response.sendRedirect(request.getContextPath()+"/admin");
			} else if (model.getRole().getCode().equals("user")) {
				response.sendRedirect(request.getContextPath()+"/home");
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/enter?action=login&message=invalid");
		}

	}

}
