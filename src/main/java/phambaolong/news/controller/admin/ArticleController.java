package phambaolong.news.controller.admin;

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

@WebServlet(urlPatterns = { "/admin-article" })
public class ArticleController extends HttpServlet {
	
	@Inject
	private IArticleService articleService;

	private static final long serialVersionUID = 8444055740588402089L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleModel model = new ArticleModel();
		model.setListItems(articleService.findAll());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/article/list-article.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
