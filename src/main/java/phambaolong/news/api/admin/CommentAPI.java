package phambaolong.news.api.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import phambaolong.news.model.CommentModel;
import phambaolong.news.model.UserModel;
import phambaolong.news.service.ICommentService;
import phambaolong.news.utils.HttpUtils;
import phambaolong.news.utils.SessionUtil;

@WebServlet (urlPatterns = {"/api-comment"})
public class CommentAPI extends HttpServlet {
	
	@Inject
	private ICommentService commentService;

	private static final long serialVersionUID = 6508669516308223315L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		CommentModel newComment = HttpUtils.toString(request.getReader()).toModel(CommentModel.class);
		String user = ((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername();
		newComment.setCreatedBy(user);
		newComment.setUserId(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getId());
		newComment = commentService.save(newComment);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), newComment);
	}
}
