package phambaolong.news.dailynews.controller.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -8064909454214279743L;
	
	protected void doGet(HttpServletRequest resquest, HttpServletResponse response) {
		RequestDispatcher rd = resquest.getRequestDispatcher("");
	}
	
}
