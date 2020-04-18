package phambaolong.news.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phambaolong.news.model.UserModel;
import phambaolong.news.service.IUserService;
import phambaolong.news.utils.FormUtil;

@WebServlet(urlPatterns = { "/home", "/login" })
public class HomeController extends HttpServlet {

	@Inject
	private IUserService userService;

	private static final long serialVersionUID = -8064909454214279743L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String message = request.getParameter("message");
		if (action != null && action.equals("login")) {
			if (message != null) {
				request.setAttribute("message", message);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = FormUtil.toModel(request, UserModel.class);
		model = userService.findBy_username_password_status(model.getUsername(), model.getPassword(), 1);
		if (model != null && model.getRole().getCode() != null) {
			if (model.getRole().getCode().equals("admin")) {
				response.sendRedirect(request.getContextPath()+"/admin");
			} else if (model.getRole().getCode().equals("user")) {
				response.sendRedirect(request.getContextPath()+"/home");
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/login?action=login&message=invalid");
		}

	}

}
