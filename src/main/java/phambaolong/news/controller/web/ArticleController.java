package phambaolong.news.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phambaolong.news.model.ArticleModel;
import phambaolong.news.service.IArticleService;
import phambaolong.news.utils.FormUtil;

@WebServlet (urlPatterns = {"/article"})
public class ArticleController extends HttpServlet{
	
	@Inject
	private IArticleService articleService;

	private static final long serialVersionUID = -1580614207126965275L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		ArticleModel model = FormUtil.toModel(request, ArticleModel.class);
		String view = "";
		if (action != null) {
			if (action.equals("detail") && model.getId() != null) {
				model = articleService.findOne(model.getId());
				if (model != null) {
					request.setAttribute("model", model);
					view = "/views/web/article/detail.jsp";
				} else {
					view = "/common/error/404.jsp";
				}
			} else if (model.getId() == null){
				view = "/common/error/404.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
