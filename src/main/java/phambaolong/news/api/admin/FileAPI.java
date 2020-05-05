package phambaolong.news.api.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

//@WebServlet (name = "FileAPI", urlPatterns = { "/api-upload-file" })
//@MultipartConfig
public class FileAPI extends HttpServlet {

	private static final long serialVersionUID = -1158840533295411389L;
	
	private static final String SAVE_DIR = "uploadFiles";

	// not done yet.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String thumbnailPath = null;
		try {
			for (Part part : request.getParts()) {
				System.out.println(part.getName().toString());
			}
			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent = filePart.getInputStream();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
		}
	    
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), thumbnailPath);
	}
}
