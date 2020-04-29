package phambaolong.news.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import phambaolong.news.model.ArticleModel;
import phambaolong.news.model.UserModel;
import phambaolong.news.service.IArticleService;
import phambaolong.news.utils.HttpUtils;
import phambaolong.news.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-article" })
public class ArticleAPI extends HttpServlet {

	@Inject
	private IArticleService articleService;

	private static final long serialVersionUID = -9125729573331900836L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		ArticleModel newArticle = HttpUtils.toString(request.getReader()).toModel(ArticleModel.class);
		newArticle.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername());
		newArticle.setUserId(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getId());
		newArticle = articleService.save(newArticle);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), newArticle);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		ArticleModel updatedArticle = HttpUtils.toString(request.getReader()).toModel(ArticleModel.class);
		updatedArticle.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername());
		updatedArticle = articleService.update(updatedArticle);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), updatedArticle);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		ArticleModel deletedList = HttpUtils.toString(request.getReader()).toModel(ArticleModel.class);
		articleService.delete(deletedList.getListId());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), "{}");
	}
}
